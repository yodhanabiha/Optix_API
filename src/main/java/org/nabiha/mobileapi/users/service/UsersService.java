package org.nabiha.mobileapi.users.service;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.users.dto.UsersRequestDTO;
import org.nabiha.mobileapi.users.dto.UsersRequestUpdateDTO;
import org.nabiha.mobileapi.users.dto.UsersResponseDTO;
import org.nabiha.mobileapi.users.exception.UsersNotFoundException;
import org.nabiha.mobileapi.users.exception.UsersServiceBusinessException;
import org.nabiha.mobileapi.users.mapper.IUsersMapper;
import org.nabiha.mobileapi.users.UsersRepository;
import org.nabiha.mobileapi.users.UsersEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersService implements IUsersService{

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
    public List<UsersResponseDTO> findAll() {
        List<UsersResponseDTO> usersResponseDTO = null;
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
