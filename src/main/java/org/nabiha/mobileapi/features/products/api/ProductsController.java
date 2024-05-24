package org.nabiha.mobileapi.features.products.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.dtos.APIResponse;
import org.nabiha.mobileapi.features.products.dtos.ProductsRequestDTO;
import org.nabiha.mobileapi.features.products.dtos.ProductsResponseDTO;
import org.nabiha.mobileapi.features.products.service.IProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductsController implements ProductsApi {

    private final IProductsService service;

    @Override
    public ResponseEntity<APIResponse<ProductsResponseDTO>> create(
            String title,
            String description,
            String spec,
            String price,
            MultipartFile image
    ) {
        ProductsResponseDTO productsResponseDTO = service.createProducts(title,description,spec,price, image);
        APIResponse<ProductsResponseDTO> response = APIResponse
                .<ProductsResponseDTO>builder()
                .status("SUCCESS")
                .results(productsResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<APIResponse<List<ProductsResponseDTO>>> findAll() {
        List<ProductsResponseDTO> productsResponseDTOS = service.findAll();
        APIResponse<List<ProductsResponseDTO>> response = APIResponse
                .<List<ProductsResponseDTO>>builder()
                .status("SUCCESS")
                .results(productsResponseDTOS)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<ProductsResponseDTO>> findById(Long id) {
        ProductsResponseDTO productsResponseDTO = service.findById(id);
        APIResponse<ProductsResponseDTO> response = APIResponse
                .<ProductsResponseDTO>builder()
                .status("SUCCESS")
                .results(productsResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<ProductsResponseDTO>> update(
            Long id,
            String title,
            String description,
            String spec,
            String price,
            MultipartFile image
    ) {
        ProductsResponseDTO productsResponseDTO = service.update(id,title,description,spec,price,image);
        APIResponse<ProductsResponseDTO> response = APIResponse
                .<ProductsResponseDTO>builder()
                .status("SUCCESS")
                .results(productsResponseDTO)
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
