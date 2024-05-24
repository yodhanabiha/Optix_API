package org.nabiha.mobileapi.features.likes.service;

import org.nabiha.mobileapi.exception.ServiceBusinessException;
import org.nabiha.mobileapi.features.likes.dtos.LikesRequestDTO;
import org.nabiha.mobileapi.features.likes.dtos.LikesResponseDTO;

import java.util.List;

public interface ILikesService {
    LikesResponseDTO createLike(LikesRequestDTO likesRequestDTO) throws ServiceBusinessException;

    List<LikesResponseDTO> findAllByUser(String email) throws ServiceBusinessException;
    List<LikesResponseDTO> findAllByProduct(Long productId) throws ServiceBusinessException;
    LikesResponseDTO findById(Long id) throws ServiceBusinessException;

    String deleteLike(Long id) throws ServiceBusinessException;

}
