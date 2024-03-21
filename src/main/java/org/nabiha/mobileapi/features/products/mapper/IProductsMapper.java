package org.nabiha.mobileapi.features.products.mapper;

import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.products.dtos.ProductsRequestDTO;
import org.nabiha.mobileapi.features.products.dtos.ProductsResponseDTO;

public interface IProductsMapper {
    ProductsEntity convertToEntity(ProductsRequestDTO users);

    ProductsResponseDTO convertToDTO(ProductsEntity usersEntity);

    ProductsEntity updateEntity(ProductsEntity existingEntity, ProductsRequestDTO updatedValues);
}
