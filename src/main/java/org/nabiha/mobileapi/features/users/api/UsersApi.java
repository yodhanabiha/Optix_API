package org.nabiha.mobileapi.features.users.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.nabiha.mobileapi.dtos.APIResponse;
import org.nabiha.mobileapi.features.carts.dtos.CartsRequestDTO;
import org.nabiha.mobileapi.features.carts.dtos.CartsResponseDTO;
import org.nabiha.mobileapi.features.users.dtos.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
@RequestMapping("/users")
public interface UsersApi {

    @PostMapping("/account/register")
    ResponseEntity<APIResponse<UsersResponseDTO>> create(@Valid @RequestBody UsersRequestDTO users);

    @PostMapping("/account/login")
    ResponseEntity<APIResponse<UsersAuthResponseDTO>> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO);

    @PostMapping("/account/google-login")
    ResponseEntity<APIResponse<UsersAuthResponseDTO>> googleLogin(@RequestParam String idToken);

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/account/profile")
    ResponseEntity<APIResponse<UsersResponseDTO>> profile(@RequestHeader("Authorization") String token);
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping()
    ResponseEntity<APIResponse<List<UsersResponseDTO>>> findAll();
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<APIResponse<UsersResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestPart("name") String name,
            @Valid @RequestPart("phone") String phone,
            @Valid @RequestPart("gender") String gender,
            @Valid @RequestPart("date_birth") String date_birth,
            @RequestPart("image") MultipartFile image
    );

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/update")
    ResponseEntity<APIResponse<UsersResponseDTO>>
    update(@RequestHeader("Authorization") String token, @RequestBody @Valid UsersRequestDTO usersRequestDTO);

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/update-password")
    ResponseEntity<APIResponse<UsersResponseDTO>>
    updatePassword(@RequestHeader("Authorization") String token, @RequestBody @Valid String password);

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<String>> delete(@PathVariable Long id);
}
