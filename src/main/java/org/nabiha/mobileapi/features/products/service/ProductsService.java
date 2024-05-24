package org.nabiha.mobileapi.features.products.service;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.exception.NotFoundException;
import org.nabiha.mobileapi.exception.ServiceBusinessException;
import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.products.ProductsRepository;
import org.nabiha.mobileapi.features.products.dtos.ProductsRequestDTO;
import org.nabiha.mobileapi.features.products.dtos.ProductsResponseDTO;
import org.nabiha.mobileapi.features.products.mapper.IProductsMapper;
import org.nabiha.mobileapi.services.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductsService implements IProductsService {

    private final IProductsMapper mapper;
    private final ProductsRepository repository;
    private final FileStorageService fileStorageService;

    @Override
    public ProductsResponseDTO createProducts(
            String title,
            String description,
            String spec,
            String price,
            MultipartFile image
    ) throws ServiceBusinessException {
        ProductsResponseDTO productsResponseDTO;
        try {
            String imageUrl = fileStorageService.storeFile(image, "PRODUCT");
            int priceInt = Integer.parseInt(price);

            ProductsRequestDTO productsRequestDTO = new ProductsRequestDTO(
                    title,
                    description,
                    spec,
                    priceInt
            );

            ProductsEntity productsEntity = mapper.convertToEntity(productsRequestDTO);
            productsEntity.setImageurl(imageUrl);
            ProductsEntity productsRes = repository.save(productsEntity);
            productsResponseDTO = mapper.convertToDTO(productsRes);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return productsResponseDTO;
    }

    @Override
    public ProductsResponseDTO findById(Long id) {
        ProductsResponseDTO productsResponseDTO;
        try {
            ProductsEntity products = repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Products Not found with id: " + id));
            productsResponseDTO = mapper.convertToDTO(products);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return productsResponseDTO;
    }

    @Override
    public List<ProductsResponseDTO> findAll() throws ServiceBusinessException {
        List<ProductsResponseDTO> productsResponseDTOS;
        try {
            List<ProductsEntity> productsEntityList = repository.findAll();
            if (!productsEntityList.isEmpty()){
                productsResponseDTOS = productsEntityList.stream().map(mapper::convertToDTO).toList();
            }else {
                productsResponseDTOS = Collections.emptyList();
            }
        }catch (Exception ex){
            throw new ServiceBusinessException(ex.getMessage());
        }
        return productsResponseDTOS;
    }

    @Override
    public ProductsResponseDTO update(
            Long id,
            String title,
            String description,
            String spec,
            String price,
            MultipartFile image
    ) {
       ProductsResponseDTO productsResponseDTO;
       try {
           ProductsEntity existing = repository.findById(id)
                   .orElseThrow(() -> new NotFoundException("Products Not found with id: " + id));

           String imageUrl = fileStorageService.storeFile(image, "PRODUCT");
           int priceInt = Integer.parseInt(price);

           ProductsRequestDTO productsRequestDTO = new ProductsRequestDTO(
                   title,
                   description,
                   spec,
                   priceInt
           );

           ProductsEntity products = mapper.updateEntity(existing, productsRequestDTO);
           products.setImageurl(imageUrl);
           ProductsEntity result = repository.save(products);
           productsResponseDTO = mapper.convertToDTO(result);
       }catch (Exception ex){
           throw new ServiceBusinessException(ex.getMessage());
       }
        return productsResponseDTO;
    }

    @Override
    public String delete(Long id) throws ServiceBusinessException {
        try {
            repository.deleteById(id);
        }catch (Exception ex){
            throw new ServiceBusinessException(ex.getMessage());
        }
        return "Products with ID: "+id+" has been deleted";
    }
}
