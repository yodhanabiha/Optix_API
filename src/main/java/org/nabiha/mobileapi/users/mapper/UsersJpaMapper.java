package org.nabiha.mobileapi.users.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nabiha.mobileapi.users.dto.UsersRequestDTO;
import org.nabiha.mobileapi.users.dto.UsersRequestUpdateDTO;
import org.nabiha.mobileapi.users.dto.UsersResponseDTO;
import org.nabiha.mobileapi.users.UsersEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UsersJpaMapper implements IUsersMapper {
    @Override
    public UsersEntity convertToEntity(UsersRequestDTO users) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setEmail(users.getEmail());
        usersEntity.setPassword(users.getPassword());
        usersEntity.setName(users.getName());
        usersEntity.setPhone(users.getPhone());
        usersEntity.setAt_created(LocalDateTime.now());
        usersEntity.setAt_updated(LocalDateTime.now());
        return usersEntity;
    }

    @Override
    public UsersResponseDTO convertToDTO(UsersEntity usersEntity) {
        return new UsersResponseDTO(
                usersEntity.getId(),
                usersEntity.getEmail(),
                usersEntity.getPassword(),
                usersEntity.getName(),
                usersEntity.getPhone(),
                usersEntity.getAt_created(),
                usersEntity.getAt_updated()
        );
    }

    @Override
    public UsersEntity updateEntity(UsersEntity existingEntity, UsersRequestUpdateDTO updatedValues) {
        existingEntity.setEmail(updatedValues.getEmail());
        existingEntity.setName(updatedValues.getName());
        existingEntity.setPhone(updatedValues.getPhone());
        existingEntity.setAt_updated(LocalDateTime.now());
        return existingEntity;
    }

    @Override
    public String jsonAsString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
