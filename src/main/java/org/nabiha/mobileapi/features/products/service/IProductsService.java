package org.nabiha.mobileapi.features.products.service;



import org.nabiha.mobileapi.features.products.dtos.ProductsRequestDTO;
import org.nabiha.mobileapi.features.products.dtos.ProductsResponseDTO;
import org.nabiha.mobileapi.features.products.exception.ProductsServiceBusinessException;

import java.util.List;

public interface IProductsService {
    ProductsResponseDTO createProducts(ProductsRequestDTO productsRequestDTO) throws ProductsServiceBusinessException;

    ProductsResponseDTO findById(Long id);

    List<ProductsResponseDTO> findAll() throws ProductsServiceBusinessException;

    ProductsResponseDTO update(ProductsRequestDTO productsRequestDTO, Long id);

    String delete(Long id) throws ProductsServiceBusinessException;

}
