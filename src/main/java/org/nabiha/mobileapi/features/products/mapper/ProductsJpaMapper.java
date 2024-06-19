package org.nabiha.mobileapi.features.products.mapper;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.features.likes.service.ILikesService;
import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.products.dtos.ProductsRequestDTO;
import org.nabiha.mobileapi.features.products.dtos.ProductsResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class ProductsJpaMapper implements IProductsMapper {

    private final ILikesService likesService;

    @Override
    public ProductsEntity convertToEntity(ProductsRequestDTO productsRequestDTO) {
        ProductsEntity productsEntity = new ProductsEntity();
        productsEntity.setTitle(productsRequestDTO.getTitle());
        productsEntity.setDescription(productsRequestDTO.getDescription());
        productsEntity.setSpec(productsRequestDTO.getSpec());
        productsEntity.setPrice(productsRequestDTO.getPrice());
        productsEntity.setImageurl("");
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
                productsEntity.getSpec(),
                productsEntity.getImageurl(),
                productsEntity.getPrice(),
                likesService.findAllByProduct(productsEntity.getId())
        );
    }

    @Override
    public ProductsEntity updateEntity(ProductsEntity existingEntity, ProductsRequestDTO updatedValues) {
        existingEntity.setTitle(updatedValues.getTitle());
        existingEntity.setImageurl("");
        existingEntity.setDescription(updatedValues.getDescription());
        existingEntity.setSpec(updatedValues.getSpec());
        existingEntity.setPrice(updatedValues.getPrice());
        existingEntity.setAt_updated(LocalDateTime.now());
        return existingEntity;
    }
}
