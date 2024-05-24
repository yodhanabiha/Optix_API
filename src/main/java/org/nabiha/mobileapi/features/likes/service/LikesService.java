package org.nabiha.mobileapi.features.likes.service;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.exception.NotFoundException;
import org.nabiha.mobileapi.exception.ServiceBusinessException;
import org.nabiha.mobileapi.features.carts.CartsEntity;
import org.nabiha.mobileapi.features.likes.LikesEntity;
import org.nabiha.mobileapi.features.likes.LikesRepository;
import org.nabiha.mobileapi.features.likes.dtos.LikesRequestDTO;
import org.nabiha.mobileapi.features.likes.dtos.LikesResponseDTO;
import org.nabiha.mobileapi.features.likes.mapper.ILikesMapper;
import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.products.ProductsRepository;
import org.nabiha.mobileapi.features.users.UsersEntity;
import org.nabiha.mobileapi.features.users.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LikesService implements ILikesService {

    private final ILikesMapper mapper;
    private final LikesRepository repository;
    private final UsersRepository usersRepository;
    private final ProductsRepository productsRepository;

    @Override
    public LikesResponseDTO createLike(LikesRequestDTO likesRequestDTO) throws ServiceBusinessException {
        LikesResponseDTO likesResponseDTO;
        try {
            LikesEntity likes = mapper.convertToEntity(likesRequestDTO);
            LikesEntity res = repository.save(likes);
            likesResponseDTO = mapper.convertToDTO(res);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return likesResponseDTO;
    }

    @Override
    public List<LikesResponseDTO> findAllByUser(String email) throws ServiceBusinessException {
        List<LikesResponseDTO> likesResponseDTO;
        try {
            UsersEntity users = usersRepository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException("Users not found with email" + email));
            List<LikesEntity> likes = repository.findAllByUser(users);
            likesResponseDTO = likes.stream().map(mapper::convertToDTO).toList();
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return likesResponseDTO;
    }

    @Override
    public List<LikesResponseDTO> findAllByProduct(Long productId) throws ServiceBusinessException {
        List<LikesResponseDTO> likesResponseDTO;
        try {
            ProductsEntity productsEntity = productsRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("Product not found with Id" + productId));

            List<LikesEntity> likesEntityList = repository.findAllByProduct(productsEntity);
            likesResponseDTO = likesEntityList.stream().map(mapper::convertToDTO).toList();
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return likesResponseDTO;
    }


    @Override
    public LikesResponseDTO findById(Long id) throws ServiceBusinessException {
        LikesResponseDTO likesResponseDTO;
        try {
            LikesEntity likes = repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("likes Not found with id: " + id));
            likesResponseDTO = mapper.convertToDTO(likes);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return likesResponseDTO;
    }

    @Override
    public String deleteLike(Long id) throws ServiceBusinessException {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return "Like with ID: " + id + " has been deleted";
    }
}
