package org.nabiha.mobileapi.features.likes.mapper;

import org.nabiha.mobileapi.features.likes.LikesEntity;
import org.nabiha.mobileapi.features.likes.dtos.LikesRequestDTO;
import org.nabiha.mobileapi.features.likes.dtos.LikesResponseDTO;

public interface ILikesMapper {
    LikesEntity convertToEntity(LikesRequestDTO likesRequestDTO);
    LikesResponseDTO convertToDTO(LikesEntity likesEntity);
}
