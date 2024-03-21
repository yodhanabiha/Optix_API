package org.nabiha.mobileapi.features.products.service;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.products.ProductsRepository;
import org.nabiha.mobileapi.features.products.dtos.ProductsRequestDTO;
import org.nabiha.mobileapi.features.products.dtos.ProductsResponseDTO;
import org.nabiha.mobileapi.features.products.exception.ProductsNotFoundException;
import org.nabiha.mobileapi.features.products.exception.ProductsServiceBusinessException;
import org.nabiha.mobileapi.features.products.mapper.IProductsMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductsService implements IProductsService {

    private final IProductsMapper mapper;
    private final ProductsRepository repository;

    @Override
    public ProductsResponseDTO createProducts(ProductsRequestDTO productsRequestDTO) throws ProductsServiceBusinessException {
        ProductsResponseDTO productsResponseDTO;
        try {
            ProductsEntity productsEntity = mapper.convertToEntity(productsRequestDTO);
            ProductsEntity productsRes = repository.save(productsEntity);
            productsResponseDTO = mapper.convertToDTO(productsRes);

        } catch (Exception ex) {
            throw new ProductsServiceBusinessException(ex.getMessage());
        }
        return productsResponseDTO;
    }

    @Override
    public ProductsResponseDTO findById(Long id) {
        ProductsResponseDTO productsResponseDTO;
        try {
            ProductsEntity products = repository.findById(id)
                    .orElseThrow(() -> new ProductsNotFoundException("Products Not found with id: " + id));
            productsResponseDTO = mapper.convertToDTO(products);
        } catch (Exception ex) {
            throw new ProductsServiceBusinessException(ex.getMessage());
        }
        return productsResponseDTO;
    }

    @Override
    public List<ProductsResponseDTO> findAll() throws ProductsServiceBusinessException {
        List<ProductsResponseDTO> productsResponseDTOS;
        try {
            List<ProductsEntity> productsEntityList = repository.findAll();
            if (!productsEntityList.isEmpty()){
                productsResponseDTOS = productsEntityList.stream().map(mapper::convertToDTO).toList();
            }else {
                productsResponseDTOS = Collections.emptyList();
            }
        }catch (Exception ex){
            throw new ProductsServiceBusinessException(ex.getMessage());
        }
        return productsResponseDTOS;
    }

    @Override
    public ProductsResponseDTO update(ProductsRequestDTO productsRequestDTO, Long id) {
       ProductsResponseDTO productsResponseDTO;
       try {
           ProductsEntity existing = repository.findById(id)
                   .orElseThrow(() -> new ProductsNotFoundException("Products Not found with id: " + id));

           ProductsEntity products = mapper.updateEntity(existing, productsRequestDTO);
           ProductsEntity result = repository.save(products);
           productsResponseDTO = mapper.convertToDTO(result);
       }catch (Exception ex){
           throw new ProductsServiceBusinessException(ex.getMessage());
       }
        return productsResponseDTO;
    }

    @Override
    public String delete(Long id) throws ProductsServiceBusinessException {
        try {
            repository.deleteById(id);
        }catch (Exception ex){
            throw new ProductsServiceBusinessException(ex.getMessage());
        }
        return "Products with ID: "+id+" has been deleted";
    }
}
