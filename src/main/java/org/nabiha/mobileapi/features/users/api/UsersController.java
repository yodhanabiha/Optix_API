package org.nabiha.mobileapi.features.users.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.config.TokenProvider;
import org.nabiha.mobileapi.dtos.APIResponse;
import org.nabiha.mobileapi.features.users.dtos.*;
import org.nabiha.mobileapi.features.users.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UsersController implements UsersApi{

    @Autowired
    private TokenProvider tokenProvider;

    private final IUsersService service;

    @Override
    public ResponseEntity<APIResponse<UsersResponseDTO>> create(UsersRequestDTO users) {
        UsersResponseDTO usersResponseDTO = service.createUser(users);
        APIResponse<UsersResponseDTO> response = APIResponse
                .<UsersResponseDTO>builder()
                .status("SUCCESS")
                .results(usersResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<APIResponse<List<UsersResponseDTO>>> findAll() {
        List<UsersResponseDTO> usersResponseDTOS = service.findAll();
        APIResponse<List<UsersResponseDTO>> response = APIResponse
                .<List<UsersResponseDTO>>builder()
                .status("SUCCESS")
                .results(usersResponseDTOS)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<UsersAuthResponseDTO>> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        UsersAuthResponseDTO usersAuthResponseDTO = service.login(loginRequestDTO);
        APIResponse<UsersAuthResponseDTO> response = APIResponse
                .<UsersAuthResponseDTO>builder()
                .status("SUCCESS")
                .results(usersAuthResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<UsersResponseDTO>> profile(String token) {
        String accessToken = token.substring(7);
        String email = tokenProvider.validateToken(accessToken);
        UsersResponseDTO usersResponseDTO = service.findByEmail(email);
        APIResponse<UsersResponseDTO> response = APIResponse
                .<UsersResponseDTO>builder()
                .status("SUCCESS")
                .results(usersResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<UsersResponseDTO>> update(Long id, UsersRequestUpdateDTO users) {
        UsersResponseDTO usersResponseDTO = service.update(users,id);
        APIResponse<UsersResponseDTO> response = APIResponse
                .<UsersResponseDTO>builder()
                .status("SUCCESS")
                .results(usersResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<String>> delete(Long id) {
        String delete = service.delete(id);
        APIResponse<String> response = APIResponse
                .<String>builder()
                .status("SUCCESS")
                .results(delete)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
