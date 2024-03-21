package org.nabiha.mobileapi.features.users.service;

import org.nabiha.mobileapi.features.users.dtos.*;
import org.nabiha.mobileapi.features.users.exception.UsersServiceBusinessException;

import java.util.List;

public interface IUsersService {

    UsersResponseDTO createUser(UsersRequestDTO usersRequestdto) throws UsersServiceBusinessException;

    UsersResponseDTO findByEmail(String email);

    List<UsersResponseDTO> findAll() throws UsersServiceBusinessException;

    UsersResponseDTO update(UsersRequestUpdateDTO usersRequestUpdateDTO, Long id);

    String delete(Long id) throws UsersServiceBusinessException;

    UsersAuthResponseDTO login(LoginRequestDTO loginRequestDTO) throws UsersServiceBusinessException;
}
