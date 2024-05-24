package org.nabiha.mobileapi.features.users.service;

import lombok.AllArgsConstructor;
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

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersService implements IUsersService {

    private final TokenProvider tokenService;
    private final IUsersMapper mapper;
    private final UsersRepository repository;
    private final FileStorageService fileStorageService;

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
            String phone, MultipartFile image
    ) {
        UsersResponseDTO usersResponseDTO;

        try {
            UsersEntity existingUser = repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

            String imageUrl = fileStorageService.storeFile(image, "USER");

            UsersRequestDTO usersRequestDTO = new UsersRequestDTO(
                    existingUser.getEmail(), existingUser.getPassword(),
                    name, phone, imageUrl, existingUser.getRole()
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
    public String delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return "User with ID: " + id + " has been deleted";
    }
}
