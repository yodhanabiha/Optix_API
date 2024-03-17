package org.nabiha.mobileapi.users.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.users.dto.APIResponse;
import org.nabiha.mobileapi.users.dto.UsersRequestDTO;
import org.nabiha.mobileapi.users.dto.UsersRequestUpdateDTO;
import org.nabiha.mobileapi.users.dto.UsersResponseDTO;
import org.nabiha.mobileapi.users.service.IUsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UsersController implements UsersApi{

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
    public ResponseEntity<APIResponse<UsersResponseDTO>> findByEmail(String email) {
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
