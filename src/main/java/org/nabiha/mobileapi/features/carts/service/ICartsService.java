package org.nabiha.mobileapi.features.carts.service;

import org.nabiha.mobileapi.exception.ServiceBusinessException;
import org.nabiha.mobileapi.features.carts.dtos.CartsRequestDTO;
import org.nabiha.mobileapi.features.carts.dtos.CartsResponseDTO;
import org.nabiha.mobileapi.features.products.dtos.ProductsRequestDTO;
import org.nabiha.mobileapi.features.products.dtos.ProductsResponseDTO;

import java.util.List;

public interface ICartsService {
    CartsResponseDTO createCarts(CartsRequestDTO cartsRequestDTO) throws ServiceBusinessException;

    CartsResponseDTO findById(Long id) throws ServiceBusinessException;

    List<CartsResponseDTO> findAll(String email) throws ServiceBusinessException;

    CartsResponseDTO update(CartsRequestDTO cartsRequestDTO, Long id) throws ServiceBusinessException;

    String delete(Long id) throws ServiceBusinessException;

    String deleteByUser(String email) throws ServiceBusinessException;
}
