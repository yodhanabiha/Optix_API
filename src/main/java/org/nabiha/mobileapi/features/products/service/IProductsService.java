package org.nabiha.mobileapi.features.products.service;


import org.nabiha.mobileapi.exception.ServiceBusinessException;
import org.nabiha.mobileapi.features.products.dtos.ProductsRequestDTO;
import org.nabiha.mobileapi.features.products.dtos.ProductsResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductsService {
    ProductsResponseDTO createProducts(
            String title,
            String description,
            String spec,
            String price,
            MultipartFile image
    ) throws ServiceBusinessException;

    ProductsResponseDTO findById(Long id);

    List<ProductsResponseDTO> findAll() throws ServiceBusinessException;

    ProductsResponseDTO update(
            Long id,
            String title,
            String description,
            String spec,
            String price,
            MultipartFile image
    );

    String delete(Long id) throws ServiceBusinessException;

}
