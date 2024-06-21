package org.nabiha.mobileapi.features.histories.mapper;

import org.nabiha.mobileapi.features.histories.HistoriesEntity;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesRequestDTO;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesResponseDTO;
import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.products.dtos.ProductResponseDTO;

public interface IHistoriesMapper {
    HistoriesEntity convertToEntity(HistoriesRequestDTO historiesRequestDTO);
    HistoriesResponseDTO convertToDTO(HistoriesEntity histories);}
