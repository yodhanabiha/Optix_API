package org.nabiha.mobileapi.users.mapper;

import org.nabiha.mobileapi.users.dto.UsersRequestDTO;
import org.nabiha.mobileapi.users.dto.UsersRequestUpdateDTO;
import org.nabiha.mobileapi.users.dto.UsersResponseDTO;
import org.nabiha.mobileapi.users.UsersEntity;

public interface IUsersMapper {
    UsersEntity convertToEntity(UsersRequestDTO users);

    UsersResponseDTO convertToDTO(UsersEntity usersEntity);

    UsersEntity updateEntity(UsersEntity existingEntity, UsersRequestUpdateDTO updatedValues);
    String jsonAsString(Object obj);
}
