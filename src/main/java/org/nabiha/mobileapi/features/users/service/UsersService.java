package org.nabiha.mobileapi.features.users.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.config.GoogleAuthService;
import org.nabiha.mobileapi.config.TokenProvider;
import org.nabiha.mobileapi.dtos.JwtDto;
import org.nabiha.mobileapi.exception.NotFoundException;
import org.nabiha.mobileapi.exception.ServiceBusinessException;
import org.nabiha.mobileapi.features.users.UsersEntity;
import org.nabiha.mobileapi.features.users.UsersRepository;
import org.nabiha.mobileapi.features.users.dtos.*;
import org.nabiha.mobileapi.features.users.mapper.IUsersMapper;
import org.nabiha.mobileapi.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersService implements IUsersService {

    private final TokenProvider tokenService;
    private final IUsersMapper mapper;
    private final UsersRepository repository;
    private final FileStorageService fileStorageService;
    private final GoogleAuthService googleAuthService;

    @Override
    public UsersResponseDTO createUser(UsersRequestDTO usersRequestdto) {
        UsersResponseDTO usersResponseDTO;
        try {
            UsersEntity users = mapper.convertToEntity(usersRequestdto);
            UsersEntity usersResult = repository.save(users);
            usersResponseDTO = mapper.convertToDTO(usersResult);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return usersResponseDTO;
    }

    @Override
    public UsersResponseDTO findByEmail(String email) {
        UsersResponseDTO usersResponseDTO;
        try {
            UsersEntity users = repository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException("Users not found with email" + email));

            usersResponseDTO = mapper.convertToDTO(users);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }

        return usersResponseDTO;
    }

    @Override
    public UsersAuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        UsersAuthResponseDTO usersAuthResponseDTO;
        try {
            UsersEntity users = repository.findByEmail(loginRequestDTO.email)
                    .orElseThrow(() -> new NotFoundException("Users not found with email " + loginRequestDTO.email));

            String password = users.getPassword();

            boolean pass = new BCryptPasswordEncoder().matches(loginRequestDTO.password, password);

            if (pass) {
                String accessToken = tokenService.generateAccessToken((users));
                UsersResponseDTO usersResponseDTO = mapper.convertToDTO(users);
                JwtDto jwtDto = new JwtDto(accessToken);
                usersAuthResponseDTO = new UsersAuthResponseDTO(usersResponseDTO, jwtDto);

            } else {
                throw new ServiceBusinessException("wrong password!");
            }

        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }

        return usersAuthResponseDTO;
    }

    public UsersAuthResponseDTO loginWithGoogle(String idTokenString) {
        try {
            GoogleIdToken.Payload payload = googleAuthService.verifyToken(idTokenString);
            String email = payload.getEmail();
            UsersEntity users = repository.findByEmail(email).orElseGet(() -> {
                UsersEntity newUser = new UsersEntity();
                newUser.setEmail(email);
                newUser.setName((String) payload.get("name"));
                newUser.setImageurl((String) payload.get("picture"));
                return repository.save(newUser);
            });

            String accessToken = tokenService.generateAccessToken(users);
            UsersResponseDTO usersResponseDTO = mapper.convertToDTO(users);
            JwtDto jwtDto = new JwtDto(accessToken);
            return new UsersAuthResponseDTO(usersResponseDTO, jwtDto);
        } catch (Exception ex) {
            throw new ServiceBusinessException("Invalid Google ID token");
        }
    }


    @Override
    public List<UsersResponseDTO> findAll() {
        List<UsersResponseDTO> usersResponseDTO;
        try {

            List<UsersEntity> users = repository.findAll();
            if (!users.isEmpty()) {
                usersResponseDTO = users.stream().map(mapper::convertToDTO).toList();
            } else {
                usersResponseDTO = Collections.emptyList();
            }

        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return usersResponseDTO;
    }

    @Override
    public UsersResponseDTO update(
            Long id, String name,
            String phone, String gender, String date_birth, MultipartFile image
    ) {
        UsersResponseDTO usersResponseDTO;

        try {
            UsersEntity existingUser = repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

            String imageUrl = fileStorageService.storeFile(image, "USER");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime birthDate = LocalDate.parse(date_birth, formatter).atStartOfDay();

            UsersRequestDTO usersRequestDTO = new UsersRequestDTO(
                    existingUser.getEmail(), existingUser.getPassword(),
                    name, phone, imageUrl, birthDate, gender, existingUser.getRole()
            );

            UsersEntity updatedUser = mapper.updateEntity(existingUser, usersRequestDTO);

            UsersEntity usersResult = repository.save(updatedUser);

            usersResponseDTO = mapper.convertToDTO(usersResult);

        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return usersResponseDTO;
    }

    @Override
    public UsersResponseDTO update(String email, UsersRequestDTO usersRequestDTO) throws ServiceBusinessException {
        UsersResponseDTO usersResponseDTO;
        try {
            UsersEntity existingUser = repository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException("User not found with ID: " + email));

            UsersEntity update = mapper.updateEntity(existingUser, usersRequestDTO);
            UsersEntity res = repository.save(update);
            usersResponseDTO = mapper.convertToDTO(res);
        }catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return usersResponseDTO;
    }

    @Override
    public UsersResponseDTO updatePassword(String email, String newPassword) throws ServiceBusinessException {
        UsersResponseDTO usersResponseDTO;
        try {
            UsersEntity existingUser = repository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException("User not found with email: " + email));

            String cleanedPassword = newPassword.replace("\"", "");

            UsersEntity updatedUser = mapper.updatePassword(existingUser, cleanedPassword);
            repository.save(updatedUser);
            usersResponseDTO = mapper.convertToDTO(updatedUser);
        }catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return usersResponseDTO;
    }

    @Override
    public String delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return "User with ID: " + id + " has been deleted";
    }
}
