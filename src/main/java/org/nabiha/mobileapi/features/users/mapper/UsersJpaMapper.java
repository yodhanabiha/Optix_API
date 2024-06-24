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
        usersEntity.setImageurl(users.getImageurl());
        usersEntity.setRole(users.getRole());
        usersEntity.setGender(users.getGender());
        usersEntity.setDate_birth(users.getDate_birth());
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
                usersEntity.getImageurl(),
                usersEntity.getRole(),
                usersEntity.getDate_birth(),
                usersEntity.getGender(),
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
        existingEntity.setImageurl(existingEntity.getImageurl());
        existingEntity.setAt_updated(LocalDateTime.now());
        existingEntity.setGender(updatedValues.getGender());
        existingEntity.setDate_birth(updatedValues.getDate_birth());
        return existingEntity;
    }

    @Override
    public UsersEntity updatePassword(UsersEntity existingEntity, String Password) {
        existingEntity.setPassword(new BCryptPasswordEncoder().encode(Password));
        existingEntity.setAt_updated(LocalDateTime.now());
        return existingEntity;
    }
}
