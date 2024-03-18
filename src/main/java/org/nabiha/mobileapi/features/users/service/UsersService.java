package org.nabiha.mobileapi.features.users.service;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.config.TokenProvider;
import org.nabiha.mobileapi.features.users.dto.*;
import org.nabiha.mobileapi.features.users.mapper.IUsersMapper;
import org.nabiha.mobileapi.features.users.dto.JwtDto;
import org.nabiha.mobileapi.features.users.UsersEntity;
import org.nabiha.mobileapi.features.users.UsersRepository;
import org.nabiha.mobileapi.features.users.exception.UsersNotFoundException;
import org.nabiha.mobileapi.features.users.exception.UsersServiceBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersService implements IUsersService{

    @Autowired
    private TokenProvider tokenService;

    private final IUsersMapper mapper;
    private final UsersRepository repository;

    @Override
    public UsersResponseDTO createUser(UsersRequestDTO usersRequestdto){
        UsersResponseDTO usersResponseDTO;
        try {
            UsersEntity users = mapper.convertToEntity(usersRequestdto);
            UsersEntity usersResult = repository.save(users);
            usersResponseDTO = mapper.convertToDTO(usersResult);
        }catch (Exception ex){
            throw new UsersServiceBusinessException(ex.getMessage());
        }
        return usersResponseDTO;
    }

    @Override
    public UsersResponseDTO findByEmail(String email) {
        UsersResponseDTO usersResponseDTO;
        try {
            UsersEntity users = repository.findByEmail(email)
                    .orElseThrow(() -> new UsersNotFoundException("Users not found with email" + email));

            usersResponseDTO = mapper.convertToDTO(users);
        }catch (Exception ex){
            throw new UsersServiceBusinessException(ex.getMessage());
        }

        return usersResponseDTO;
    }

    @Override
    public UsersAuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        UsersAuthResponseDTO usersAuthResponseDTO;
        try {
            UsersEntity users = repository.findByEmail(loginRequestDTO.email)
                    .orElseThrow(() -> new UsersNotFoundException("Users not found with email" + loginRequestDTO.email));

            String password = users.getPassword();

            boolean pass = new BCryptPasswordEncoder().matches(loginRequestDTO.password, password);

            if(pass) {
                String accessToken = tokenService.generateAccessToken((users));
                UsersResponseDTO usersResponseDTO = mapper.convertToDTO(users);
                JwtDto jwtDto = new JwtDto(accessToken);
                usersAuthResponseDTO = new UsersAuthResponseDTO(usersResponseDTO,jwtDto);

            }else {
                throw new UsersServiceBusinessException("wrong password!");
            }

        }catch (Exception ex){
            throw new UsersServiceBusinessException(ex.getMessage());
        }

        return usersAuthResponseDTO;
    }


    @Override
    public List<UsersResponseDTO> findAll() {
        List<UsersResponseDTO> usersResponseDTO;
        try {

            List<UsersEntity> users = repository.findAll();
            if (!users.isEmpty()){
                usersResponseDTO = users.stream().map(mapper::convertToDTO).toList();
            }else{
                usersResponseDTO = Collections.emptyList();
            }

        }catch (Exception ex){
            throw new UsersServiceBusinessException(ex.getMessage());
        }
        return usersResponseDTO;
    }

    @Override
    public UsersResponseDTO update(UsersRequestUpdateDTO usersRequestUpdateDTO, Long id) {
        UsersResponseDTO usersResponseDTO;

        try {
            UsersEntity existingUser = repository.findById(id)
                    .orElseThrow(() -> new UsersNotFoundException("User not found with ID: " + id));

            UsersEntity updatedUser = mapper.updateEntity(existingUser, usersRequestUpdateDTO);

            UsersEntity usersResult = repository.save(updatedUser);

            usersResponseDTO = mapper.convertToDTO(usersResult);

        }catch (Exception ex){
            throw new UsersServiceBusinessException(ex.getMessage());
        }
        return usersResponseDTO;
    }

    @Override
    public String delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (Exception ex){
            throw new UsersServiceBusinessException(ex.getMessage());
        }
        return "User with ID: "+id+" has been deleted";
    }
}
