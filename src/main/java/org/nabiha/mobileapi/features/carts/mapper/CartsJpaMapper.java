package org.nabiha.mobileapi.features.carts.mapper;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.exception.NotFoundException;
import org.nabiha.mobileapi.features.carts.CartsEntity;
import org.nabiha.mobileapi.features.carts.dtos.CartsRequestDTO;
import org.nabiha.mobileapi.features.carts.dtos.CartsResponseDTO;
import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.products.ProductsRepository;
import org.nabiha.mobileapi.features.products.dtos.ProductsResponseDTO;
import org.nabiha.mobileapi.features.products.mapper.IProductsMapper;
import org.nabiha.mobileapi.features.users.UsersEntity;
import org.nabiha.mobileapi.features.users.UsersRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class CartsJpaMapper implements ICartsMapper{

    private final ProductsRepository productsRepository;
    private final UsersRepository usersRepository;
    private final IProductsMapper productsMapper;

    @Override
    public CartsEntity convertToEntity(CartsRequestDTO cartsRequestDTO) {
        CartsEntity cartsEntity = new CartsEntity();
        ProductsEntity productsEntity = productsRepository.findById(cartsRequestDTO.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));
        UsersEntity usersEntity = usersRepository.findById(cartsRequestDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        cartsEntity.setProduct(productsEntity);
        cartsEntity.setUser(usersEntity);
        cartsEntity.setTotal(cartsRequestDTO.getTotal());
        cartsEntity.setSelected(cartsRequestDTO.getSelected());
        cartsEntity.setAt_created(LocalDateTime.now());
        cartsEntity.setAt_updated(LocalDateTime.now());
        return cartsEntity;
    }

    @Override
    public CartsEntity updateEntity(CartsEntity existingEntity, CartsRequestDTO updatedValue) {
        existingEntity.setTotal(updatedValue.getTotal());
        existingEntity.setSelected(updatedValue.getSelected());
        existingEntity.setAt_updated(LocalDateTime.now());
        return existingEntity;
    }

    @Override
    public CartsResponseDTO convertToDTO(CartsEntity cartsEntity) {
        ProductsResponseDTO productsResponseDTO = productsMapper.convertToDTO(cartsEntity.getProduct());
        CartsResponseDTO cartsResponseDTO = new CartsResponseDTO();
        cartsResponseDTO.setId(cartsEntity.getId());
        cartsResponseDTO.setProduct(productsResponseDTO);
        cartsResponseDTO.setUserId(cartsEntity.getUser().getId());
        cartsResponseDTO.setAt_updated(cartsEntity.getAt_updated());
        cartsResponseDTO.setAt_created(cartsEntity.getAt_created());
        return cartsResponseDTO;
    }
}
