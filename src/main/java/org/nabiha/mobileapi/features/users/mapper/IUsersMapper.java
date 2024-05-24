package org.nabiha.mobileapi.features.users.mapper;

import org.nabiha.mobileapi.features.users.UsersEntity;
import org.nabiha.mobileapi.features.users.dtos.UsersRequestDTO;
import org.nabiha.mobileapi.features.users.dtos.UsersResponseDTO;

public interface IUsersMapper {
    UsersEntity convertToEntity(UsersRequestDTO users);

    UsersResponseDTO convertToDTO(UsersEntity usersEntity);

    UsersEntity updateEntity(UsersEntity existingEntity, UsersRequestDTO updatedValues);
}
