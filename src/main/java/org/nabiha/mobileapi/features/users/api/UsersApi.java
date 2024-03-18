package org.nabiha.mobileapi.features.users.api;

import jakarta.validation.Valid;
import org.nabiha.mobileapi.features.users.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public interface UsersApi {

    @PostMapping("/account/register")
    ResponseEntity<APIResponse<UsersResponseDTO>> create(@Valid @RequestBody UsersRequestDTO users);

    @PostMapping("/account/login")
    ResponseEntity<APIResponse<UsersAuthResponseDTO>> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO);

    @GetMapping("/account/profile")
    ResponseEntity<APIResponse<UsersResponseDTO>> profile(@RequestHeader("Authorization") String token);

    @GetMapping()
    ResponseEntity<APIResponse<List<UsersResponseDTO>>> findAll();

    @PutMapping("/{id}")
    ResponseEntity<APIResponse<UsersResponseDTO>> update(@PathVariable Long id, @RequestBody @Valid UsersRequestUpdateDTO users);

    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<String>> delete(@PathVariable Long id);
}
