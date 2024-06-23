package org.nabiha.mobileapi.features.histories.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoriesRequestBatchDTO {

    @NotNull(message = "histories shouldn't be NULL")
    private List<HistoriesRequestDTO> histories;
}

