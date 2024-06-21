package org.nabiha.mobileapi.features.histories.mapper;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.exception.NotFoundException;
import org.nabiha.mobileapi.features.histories.HistoriesEntity;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesRequestDTO;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesResponseDTO;
import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.products.ProductsRepository;
import org.nabiha.mobileapi.features.products.dtos.ProductResponseDTO;
import org.nabiha.mobileapi.features.products.mapper.IProductsMapper;
import org.nabiha.mobileapi.features.users.UsersEntity;
import org.nabiha.mobileapi.features.users.UsersRepository;
import org.nabiha.mobileapi.features.users.mapper.IUsersMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class HistoriesMapper implements IHistoriesMapper{

    private final ProductsRepository productsRepository;
    private final UsersRepository usersRepository;
    private final IProductsMapper productsMapper;

    @Override
    public HistoriesEntity convertToEntity(HistoriesRequestDTO historiesRequestDTO) {
        HistoriesEntity histories = new HistoriesEntity();

        ProductsEntity productsEntity = productsRepository.findById(historiesRequestDTO.getProduct_id())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        UsersEntity usersEntity = usersRepository.findById(historiesRequestDTO.getUser_id())
                .orElseThrow(() -> new NotFoundException("User not found"));

        histories.setPrice_item(historiesRequestDTO.getPrice_item());
        histories.setTotal_item(historiesRequestDTO.getTotal_item());
        histories.setTotal_price(historiesRequestDTO.getTotal_price());
        histories.setPurchase_date(LocalDateTime.now());
        histories.setShipping(historiesRequestDTO.getShipping());
        histories.setAddress(historiesRequestDTO.getAddress());
        histories.setProduct(productsEntity);
        histories.setUser(usersEntity);
        histories.setAt_created(LocalDateTime.now());
        histories.setAt_updated(LocalDateTime.now());
        return histories;
    }

    @Override
    public HistoriesResponseDTO convertToDTO(HistoriesEntity histories) {
        HistoriesResponseDTO historiesResponseDTO = new HistoriesResponseDTO();
        historiesResponseDTO.setId(histories.getId());
        historiesResponseDTO.setPrice_item(histories.getPrice_item());
        historiesResponseDTO.setTotal_item(histories.getTotal_item());
        historiesResponseDTO.setTotal_price(histories.getTotal_price());
        historiesResponseDTO.setPurchase_date(histories.getPurchase_date());
        historiesResponseDTO.setShipping(histories.getShipping());
        historiesResponseDTO.setAddress(histories.getAddress());
        historiesResponseDTO.setProduct(productsMapper.convertToDTO(histories.getProduct()));
        historiesResponseDTO.setUser_id(histories.getUser().getId());
        return historiesResponseDTO;
    }

}
