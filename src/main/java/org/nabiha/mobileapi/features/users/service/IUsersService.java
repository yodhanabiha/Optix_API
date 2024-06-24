package org.nabiha.mobileapi.features.users.service;

import org.nabiha.mobileapi.exception.ServiceBusinessException;
import org.nabiha.mobileapi.features.users.dtos.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface IUsersService {

    UsersResponseDTO createUser(UsersRequestDTO usersRequestdto) throws ServiceBusinessException;

    UsersResponseDTO findByEmail(String email);

    UsersAuthResponseDTO loginWithGoogle(String idTokenString) throws ServiceBusinessException;

    List<UsersResponseDTO> findAll() throws ServiceBusinessException;

    UsersResponseDTO update(Long id, String name,
                            String phone, String gender,
                            String date_birth, MultipartFile image);

    UsersResponseDTO update(String email, UsersRequestDTO usersRequestDTO) throws ServiceBusinessException;
    UsersResponseDTO updatePassword(String email, String newPassword) throws ServiceBusinessException;

    String delete(Long id) throws ServiceBusinessException;

    UsersAuthResponseDTO login(LoginRequestDTO loginRequestDTO) throws ServiceBusinessException;
}
