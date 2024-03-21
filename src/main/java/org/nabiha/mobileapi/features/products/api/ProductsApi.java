package org.nabiha.mobileapi.features.products.api;

import jakarta.validation.Valid;
import org.nabiha.mobileapi.dtos.APIResponse;
import org.nabiha.mobileapi.features.products.dtos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
public interface ProductsApi {

    @PostMapping()
    ResponseEntity<APIResponse<ProductsResponseDTO>> create(@Valid @RequestBody ProductsRequestDTO productsRequestDTO);

    @GetMapping()
    ResponseEntity<APIResponse<List<ProductsResponseDTO>>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<APIResponse<ProductsResponseDTO>> findById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<APIResponse<ProductsResponseDTO>> update(@PathVariable Long id, @RequestBody @Valid ProductsRequestDTO productsRequestDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<String>> delete(@PathVariable Long id);
}
