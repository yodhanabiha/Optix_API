package org.nabiha.mobileapi.features.users.mapper;

import org.nabiha.mobileapi.features.users.UsersEntity;
import org.nabiha.mobileapi.features.users.dtos.UsersRequestDTO;
import org.nabiha.mobileapi.features.users.dtos.UsersResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UsersJpaMapper implements IUsersMapper {
    @Override
    public UsersEntity convertToEntity(UsersRequestDTO users) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setEmail(users.getEmail());
        usersEntity.setPassword(new BCryptPasswordEncoder().encode(users.getPassword()));
        usersEntity.setName(users.getName());
        usersEntity.setPhone(users.getPhone());
        usersEntity.setRole(users.getRole());
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
                null,
                usersEntity.getRole(),
                usersEntity.getAt_created(),
                usersEntity.getAt_updated()
        );
    }

    @Override
    public UsersEntity updateEntity(UsersEntity existingEntity, UsersRequestDTO updatedValues) {
        existingEntity.setEmail(updatedValues.getEmail());
        existingEntity.setName(updatedValues.getName());
        existingEntity.setPhone(updatedValues.getPhone());
        existingEntity.setImageurl(updatedValues.getImageurl());
        existingEntity.setRole(updatedValues.getRole());
        existingEntity.setAt_updated(LocalDateTime.now());
        return existingEntity;
    }
}
