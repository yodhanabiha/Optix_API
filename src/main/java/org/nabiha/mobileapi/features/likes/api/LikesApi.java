package org.nabiha.mobileapi.features.likes.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.nabiha.mobileapi.dtos.APIResponse;
import org.nabiha.mobileapi.features.likes.dtos.LikesRequestDTO;
import org.nabiha.mobileapi.features.likes.dtos.LikesResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/likes")
public interface LikesApi {

    @PostMapping
    ResponseEntity<APIResponse<LikesResponseDTO>>
    create(@Valid @RequestBody LikesRequestDTO likesRequestDTO);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<APIResponse<String>>
    delete(@PathVariable Long id);

    @GetMapping
    ResponseEntity<APIResponse<List<LikesResponseDTO>>>
    findAll(@RequestHeader ("Authorization") String token);

    @GetMapping("/{id}")
    ResponseEntity<APIResponse<LikesResponseDTO>>
    findById(@PathVariable Long id);

}
