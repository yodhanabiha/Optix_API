package org.nabiha.mobileapi.features.histories.api;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.config.TokenProvider;
import org.nabiha.mobileapi.dtos.APIResponse;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesRequestBatchDTO;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesRequestDTO;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesResponseDTO;
import org.nabiha.mobileapi.features.histories.service.IHistoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class HistoriesController implements HistoriesApi{

    private final IHistoriesService service;
    private final TokenProvider tokenProvider;

    @Override
    public ResponseEntity<APIResponse<List<HistoriesResponseDTO>>> create(HistoriesRequestBatchDTO historiesRequestBatchDTO) {
        List<HistoriesResponseDTO> historiesResponseDTO = service.createHistories(historiesRequestBatchDTO);
        APIResponse<List<HistoriesResponseDTO>> response = APIResponse
                .<List<HistoriesResponseDTO>>builder()
                .status("SUCCESS")
                .results(historiesResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<APIResponse<List<HistoriesResponseDTO>>> findAll(String token) {
        String accessToken = token.substring(7);
        String email = tokenProvider.validateToken(accessToken);
        List<HistoriesResponseDTO> historiesResponseDTOS = service.findAllByUser(email);
        APIResponse<List<HistoriesResponseDTO>> response = APIResponse
                .<List<HistoriesResponseDTO>>builder()
                .status("SUCCESS")
                .results(historiesResponseDTOS)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<HistoriesResponseDTO>> findById(Long id) {
        HistoriesResponseDTO historiesResponseDTO = service.findById(id);
        APIResponse<HistoriesResponseDTO> response = APIResponse
                .<HistoriesResponseDTO>builder()
                .status("SUCCESS")
                .results(historiesResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<String>> delete(Long id) {
        String delete = service.deleteHistory(id);
        APIResponse<String> response = APIResponse
                .<String>builder()
                .status("SUCCESS")
                .results(delete)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
