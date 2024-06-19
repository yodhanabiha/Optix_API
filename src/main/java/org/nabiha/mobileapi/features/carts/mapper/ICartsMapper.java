package org.nabiha.mobileapi.features.carts.mapper;

import org.nabiha.mobileapi.features.carts.CartsEntity;
import org.nabiha.mobileapi.features.carts.dtos.CartsRequestDTO;
import org.nabiha.mobileapi.features.carts.dtos.CartsResponseDTO;
import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.products.dtos.ProductResponseDTO;

public interface ICartsMapper {
    CartsEntity convertToEntity(CartsRequestDTO cartsRequestDTO);

    CartsEntity updateEntity(CartsEntity existingEntity,CartsRequestDTO updatedValue);

    CartsResponseDTO convertToDTO(CartsEntity cartsEntity);

    ProductResponseDTO convertToDTOProduct(ProductsEntity productsEntity);
}
