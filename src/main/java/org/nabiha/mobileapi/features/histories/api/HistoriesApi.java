package org.nabiha.mobileapi.features.histories.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.nabiha.mobileapi.dtos.APIResponse;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesRequestBatchDTO;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesRequestDTO;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/histories")
public interface HistoriesApi {
    @PostMapping()
    ResponseEntity<APIResponse<List<HistoriesResponseDTO>>>
    create(@Valid @RequestBody HistoriesRequestBatchDTO historiesRequestBatchDTO);

    @GetMapping()
    ResponseEntity<APIResponse<List<HistoriesResponseDTO>>>
    findAll(@RequestHeader("Authorization") String token);

    @GetMapping("/{id}")
    ResponseEntity<APIResponse<HistoriesResponseDTO>>
    findById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<String>>
    delete(@PathVariable Long id);
}
