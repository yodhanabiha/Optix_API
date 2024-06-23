package org.nabiha.mobileapi.features.histories.service;

import org.nabiha.mobileapi.exception.ServiceBusinessException;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesRequestBatchDTO;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesRequestDTO;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesResponseDTO;


import java.util.List;

public interface IHistoriesService {
    List<HistoriesResponseDTO> createHistories(HistoriesRequestBatchDTO historiesRequestBatchDTO) throws ServiceBusinessException;
    List<HistoriesResponseDTO> findAllByUser(String email) throws ServiceBusinessException;

    HistoriesResponseDTO findById(Long id) throws ServiceBusinessException;
    String deleteHistory(Long id) throws ServiceBusinessException;
}
