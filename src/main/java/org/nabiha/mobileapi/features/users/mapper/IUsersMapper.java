package org.nabiha.mobileapi.features.users.mapper;

import org.nabiha.mobileapi.features.users.UsersEntity;
import org.nabiha.mobileapi.features.users.dto.UsersRequestDTO;
import org.nabiha.mobileapi.features.users.dto.UsersRequestUpdateDTO;
import org.nabiha.mobileapi.features.users.dto.UsersResponseDTO;

public interface IUsersMapper {
    UsersEntity convertToEntity(UsersRequestDTO users);

    UsersResponseDTO convertToDTO(UsersEntity usersEntity);

    UsersEntity updateEntity(UsersEntity existingEntity, UsersRequestUpdateDTO updatedValues);
    String jsonAsString(Object obj);
}
