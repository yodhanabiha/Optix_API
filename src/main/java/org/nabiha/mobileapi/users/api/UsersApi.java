package org.nabiha.mobileapi.users.api;

import jakarta.validation.Valid;
import org.nabiha.mobileapi.users.dto.APIResponse;
import org.nabiha.mobileapi.users.dto.UsersRequestDTO;
import org.nabiha.mobileapi.users.dto.UsersRequestUpdateDTO;
import org.nabiha.mobileapi.users.dto.UsersResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public interface UsersApi {
    @PostMapping()
    ResponseEntity<APIResponse<UsersResponseDTO>> create(@Valid @RequestBody  UsersRequestDTO users);

    @GetMapping("/email/{email}")
    ResponseEntity<APIResponse<UsersResponseDTO>> findByEmail(@PathVariable String email);

    @GetMapping()
    ResponseEntity<APIResponse<List<UsersResponseDTO>>> findAll();

    @PutMapping("/{id}")
    ResponseEntity<APIResponse<UsersResponseDTO>> update(@PathVariable Long id, @RequestBody @Valid UsersRequestUpdateDTO users);

    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<String>> delete(@PathVariable Long id);
}
