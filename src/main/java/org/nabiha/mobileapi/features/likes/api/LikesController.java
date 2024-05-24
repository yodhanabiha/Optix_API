package org.nabiha.mobileapi.features.likes.api;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.config.TokenProvider;
import org.nabiha.mobileapi.dtos.APIResponse;
import org.nabiha.mobileapi.features.likes.dtos.LikesRequestDTO;
import org.nabiha.mobileapi.features.likes.dtos.LikesResponseDTO;
import org.nabiha.mobileapi.features.likes.service.ILikesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class LikesController implements LikesApi {

    private final TokenProvider tokenProvider;
    private final ILikesService service;

    @Override
    public ResponseEntity<APIResponse<LikesResponseDTO>> create(LikesRequestDTO likesRequestDTO) {
        LikesResponseDTO likesResponseDTO = service.createLike(likesRequestDTO);
        APIResponse<LikesResponseDTO> response = APIResponse
                .<LikesResponseDTO>builder()
                .status("SUCCESS")
                .results(likesResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<APIResponse<String>> delete(Long id) {
        String delete = service.deleteLike(id);
        APIResponse<String> response = APIResponse
                .<String>builder()
                .status("SUCCESS")
                .results(delete)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<List<LikesResponseDTO>>> findAll(String token) {
        String accessToken = token.substring(7);
        String email = tokenProvider.validateToken(accessToken);
        List<LikesResponseDTO> likesResponseDTOS = service.findAllByUser(email);
        APIResponse<List<LikesResponseDTO>> response = APIResponse
                .<List<LikesResponseDTO>>builder()
                .status("SUCCESS")
                .results(likesResponseDTOS)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<LikesResponseDTO>> findById(Long id) {
        LikesResponseDTO likesResponseDTO = service.findById(id);
        APIResponse<LikesResponseDTO> response = APIResponse
                .<LikesResponseDTO>builder()
                .status("SUCCESS")
                .results(likesResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
