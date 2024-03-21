package org.nabiha.mobileapi.features.products.mapper;

import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.products.dtos.ProductsRequestDTO;
import org.nabiha.mobileapi.features.products.dtos.ProductsResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductsJpaMapper implements IProductsMapper{

    @Override
    public ProductsEntity convertToEntity(ProductsRequestDTO productsRequestDTO) {
        ProductsEntity productsEntity = new ProductsEntity();
        productsEntity.setTitle(productsRequestDTO.getTitle());
        productsEntity.setDescription(productsRequestDTO.getDescription());
        productsEntity.setPrice(productsRequestDTO.getPrice());
        productsEntity.setImageurl(productsRequestDTO.getImageurl());
        productsEntity.setAt_updated(LocalDateTime.now());
        productsEntity.setAt_created(LocalDateTime.now());
        return productsEntity;
    }

    @Override
    public ProductsResponseDTO convertToDTO(ProductsEntity productsEntity) {
        return new ProductsResponseDTO(
                productsEntity.getId(),
                productsEntity.getTitle(),
                productsEntity.getDescription(),
                productsEntity.getImageurl(),
                productsEntity.getPrice()
        );
    }

    @Override
    public ProductsEntity updateEntity(ProductsEntity existingEntity, ProductsRequestDTO updatedValues) {
        existingEntity.setTitle(updatedValues.getTitle());
        existingEntity.setImageurl(updatedValues.getImageurl());
        existingEntity.setDescription(updatedValues.getDescription());
        existingEntity.setPrice(updatedValues.getPrice());
        existingEntity.setAt_updated(LocalDateTime.now());
        return existingEntity;
    }
}
