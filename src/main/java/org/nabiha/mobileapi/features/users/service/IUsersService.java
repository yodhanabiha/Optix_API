package org.nabiha.mobileapi.features.users.service;

import org.nabiha.mobileapi.exception.ServiceBusinessException;
import org.nabiha.mobileapi.features.users.dtos.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUsersService {

    UsersResponseDTO createUser(UsersRequestDTO usersRequestdto) throws ServiceBusinessException;

    UsersResponseDTO findByEmail(String email);

    List<UsersResponseDTO> findAll() throws ServiceBusinessException;

    UsersResponseDTO update(Long id, String name,
                            String phone, MultipartFile image);

    String delete(Long id) throws ServiceBusinessException;

    UsersAuthResponseDTO login(LoginRequestDTO loginRequestDTO) throws ServiceBusinessException;
}
