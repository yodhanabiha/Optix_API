package org.nabiha.mobileapi.features.products.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.nabiha.mobileapi.dtos.APIResponse;
import org.nabiha.mobileapi.features.products.dtos.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/products")
public interface ProductsApi {

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<APIResponse<ProductsResponseDTO>> create(
            @Valid @RequestPart("title") String title,
            @Valid @RequestPart("description") String description,
            @Valid @RequestPart("spec") String spec,
            @Valid @RequestPart("price") String price,
            @RequestPart("image") MultipartFile image
    );

    @GetMapping()
    ResponseEntity<APIResponse<List<ProductsResponseDTO>>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<APIResponse<ProductsResponseDTO>> findById(@PathVariable Long id);

    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<APIResponse<ProductsResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestPart("title") String title,
            @Valid @RequestPart("description") String description,
            @Valid @RequestPart("spec") String spec,
            @Valid @RequestPart("price") String price,
            @RequestPart("image") MultipartFile image
    );

    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<String>> delete(@PathVariable Long id);
}
