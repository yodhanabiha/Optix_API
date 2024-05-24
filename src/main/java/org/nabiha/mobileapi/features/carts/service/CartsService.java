package org.nabiha.mobileapi.features.carts.service;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.exception.NotFoundException;
import org.nabiha.mobileapi.exception.ServiceBusinessException;
import org.nabiha.mobileapi.features.carts.CartsEntity;
import org.nabiha.mobileapi.features.carts.CartsRepository;
import org.nabiha.mobileapi.features.carts.dtos.CartsRequestDTO;
import org.nabiha.mobileapi.features.carts.dtos.CartsResponseDTO;
import org.nabiha.mobileapi.features.carts.mapper.ICartsMapper;
import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.products.ProductsRepository;
import org.nabiha.mobileapi.features.products.dtos.ProductsResponseDTO;
import org.nabiha.mobileapi.features.products.mapper.IProductsMapper;
import org.nabiha.mobileapi.features.users.UsersEntity;
import org.nabiha.mobileapi.features.users.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartsService implements ICartsService {

    private final ICartsMapper mapper;
    private final CartsRepository repository;
    private final UsersRepository usersRepository;

    @Override
    public CartsResponseDTO createCarts(CartsRequestDTO cartsRequestDTO) throws ServiceBusinessException {
        CartsResponseDTO cartsResponseDTO;
        try {
            CartsEntity cartsEntity = mapper.convertToEntity(cartsRequestDTO);
            CartsEntity cartsRes = repository.save(cartsEntity);
            cartsResponseDTO = mapper.convertToDTO(cartsRes);

        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return cartsResponseDTO;
    }

    @Override
    public CartsResponseDTO findById(Long id) throws ServiceBusinessException {
        CartsResponseDTO cartsResponseDTO;
        try {
            CartsEntity carts = repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Carts Not found with id: " + id));
            cartsResponseDTO = mapper.convertToDTO(carts);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return cartsResponseDTO;
    }

    @Override
    public List<CartsResponseDTO> findAll(String email) throws ServiceBusinessException {
        List<CartsResponseDTO> cartsResponseDTOS;
        try {
            UsersEntity users = usersRepository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException("Users not found with email" + email));
            List<CartsEntity> cartsEntityList = repository.findAllByUser(users);
            cartsResponseDTOS = cartsEntityList.stream().map(mapper::convertToDTO).toList();
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return cartsResponseDTOS;
    }

    @Override
    public CartsResponseDTO update(CartsRequestDTO cartsRequestDTO, Long id) throws ServiceBusinessException {
        CartsResponseDTO cartsResponseDTO;
        try {
            CartsEntity existing = repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Carts Not found with id: " + id));
            CartsEntity carts = mapper.updateEntity(existing, cartsRequestDTO);
            CartsEntity res = repository.save(carts);
            cartsResponseDTO = mapper.convertToDTO(res);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return cartsResponseDTO;
    }

    @Override
    public String delete(Long id) throws ServiceBusinessException {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return "Carts with ID: " + id + " has been deleted";
    }
}



