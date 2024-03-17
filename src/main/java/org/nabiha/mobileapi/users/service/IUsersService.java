package org.nabiha.mobileapi.users.service;

import org.nabiha.mobileapi.users.dto.UsersRequestDTO;
import org.nabiha.mobileapi.users.dto.UsersRequestUpdateDTO;
import org.nabiha.mobileapi.users.dto.UsersResponseDTO;
import org.nabiha.mobileapi.users.exception.UsersServiceBusinessException;

import java.util.List;

public interface IUsersService {

    UsersResponseDTO createUser(UsersRequestDTO usersRequestdto) throws UsersServiceBusinessException;

    UsersResponseDTO findByEmail(String email);

    List<UsersResponseDTO> findAll() throws UsersServiceBusinessException;

    UsersResponseDTO update(UsersRequestUpdateDTO usersRequestUpdateDTO, Long id);

    String delete(Long id) throws UsersServiceBusinessException;
}
