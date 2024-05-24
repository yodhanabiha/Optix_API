package org.nabiha.mobileapi.features.likes.mapper;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.exception.NotFoundException;
import org.nabiha.mobileapi.features.carts.CartsEntity;
import org.nabiha.mobileapi.features.carts.dtos.CartsRequestDTO;
import org.nabiha.mobileapi.features.likes.LikesEntity;
import org.nabiha.mobileapi.features.likes.dtos.LikesRequestDTO;
import org.nabiha.mobileapi.features.likes.dtos.LikesResponseDTO;
import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.products.ProductsRepository;
import org.nabiha.mobileapi.features.users.UsersEntity;
import org.nabiha.mobileapi.features.users.UsersRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LikesMapper implements ILikesMapper {

    private final ProductsRepository productsRepository;
    private final UsersRepository usersRepository;

    @Override
    public LikesEntity convertToEntity(LikesRequestDTO likesRequestDTO) {
        LikesEntity likesEntity = new LikesEntity();
        ProductsEntity productsEntity = productsRepository.findById(likesRequestDTO.getProduct_id())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        UsersEntity usersEntity = usersRepository.findById(likesRequestDTO.getUser_id())
                .orElseThrow(() -> new NotFoundException("User not found"));

        likesEntity.setUser(usersEntity);
        likesEntity.setProduct(productsEntity);
        return likesEntity;
    }

    @Override
    public LikesResponseDTO convertToDTO(LikesEntity likesEntity) {
        LikesResponseDTO likesResponseDTO = new LikesResponseDTO();
        likesResponseDTO.setId(likesEntity.getId());
        likesResponseDTO.setProduct_id(likesEntity.getProduct().getId());
        likesResponseDTO.setUser_id(likesEntity.getUser().getId());
        return likesResponseDTO;
    }
}
