package org.nabiha.mobileapi.features.carts.api;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.config.TokenProvider;
import org.nabiha.mobileapi.dtos.APIResponse;
import org.nabiha.mobileapi.features.carts.dtos.CartsRequestDTO;
import org.nabiha.mobileapi.features.carts.dtos.CartsResponseDTO;
import org.nabiha.mobileapi.features.carts.service.ICartsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CartsController implements CartsApi{

    private final ICartsService service;
    private final TokenProvider tokenProvider;

    @Override
    public ResponseEntity<APIResponse<CartsResponseDTO>> create(CartsRequestDTO cartsRequestDTO) {
        CartsResponseDTO cartsResponseDTO = service.createCarts(cartsRequestDTO);
        APIResponse<CartsResponseDTO> response = APIResponse
                .<CartsResponseDTO>builder()
                .status("SUCCESS")
                .results(cartsResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<APIResponse<List<CartsResponseDTO>>> findAll(String token) {
        String accessToken = token.substring(7);
        String email = tokenProvider.validateToken(accessToken);
        List<CartsResponseDTO> cartsResponseDTO = service.findAll(email);
        APIResponse<List<CartsResponseDTO>> response = APIResponse
                .<List<CartsResponseDTO>>builder()
                .status("SUCCESS")
                .results(cartsResponseDTO)
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<CartsResponseDTO>> findById(Long id) {
        CartsResponseDTO cartsResponseDTO = service.findById(id);
        APIResponse<CartsResponseDTO> response = APIResponse
                .<CartsResponseDTO>builder()
                .status("SUCCESS")
                .results(cartsResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<CartsResponseDTO>> update(Long id, CartsRequestDTO cartsRequestDTO) {
        CartsResponseDTO cartsResponseDTO = service.update(cartsRequestDTO, id);
        APIResponse<CartsResponseDTO> response = APIResponse
                .<CartsResponseDTO>builder()
                .status("SUCCESS")
                .results(cartsResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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

    @Override
    public ResponseEntity<APIResponse<String>> deleteByUser(String token) {
        String accessToken = token.substring(7);
        String email = tokenProvider.validateToken(accessToken);
        String delete = service.deleteByUser(email);
        APIResponse<String> response = APIResponse
                .<String>builder()
                .status("SUCCESS")
                .results(delete)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
