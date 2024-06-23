package org.nabiha.mobileapi.features.carts.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.nabiha.mobileapi.dtos.APIResponse;
import org.nabiha.mobileapi.features.carts.dtos.CartsRequestDTO;
import org.nabiha.mobileapi.features.carts.dtos.CartsResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/carts")
public interface CartsApi {
    @PostMapping()
    ResponseEntity<APIResponse<CartsResponseDTO>>
    create(@Valid @RequestBody CartsRequestDTO cartsRequestDTO);

    @GetMapping()
    ResponseEntity<APIResponse<List<CartsResponseDTO>>>
    findAll(@RequestHeader("Authorization") String token);

    @GetMapping("/{id}")
    ResponseEntity<APIResponse<CartsResponseDTO>>
    findById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<APIResponse<CartsResponseDTO>>
    update(@PathVariable Long id, @RequestBody @Valid CartsRequestDTO cartsRequestDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<String>>
    delete(@PathVariable Long id);

    @DeleteMapping()
    ResponseEntity<APIResponse<String>>
    deleteByUser(@RequestHeader("Authorization") String token);
}
