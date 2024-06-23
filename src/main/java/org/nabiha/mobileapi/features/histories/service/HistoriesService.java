package org.nabiha.mobileapi.features.histories.service;

import lombok.AllArgsConstructor;
import org.nabiha.mobileapi.exception.NotFoundException;
import org.nabiha.mobileapi.exception.ServiceBusinessException;
import org.nabiha.mobileapi.features.histories.HistoriesEntity;
import org.nabiha.mobileapi.features.histories.HistoriesRepository;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesRequestBatchDTO;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesRequestDTO;
import org.nabiha.mobileapi.features.histories.dtos.HistoriesResponseDTO;
import org.nabiha.mobileapi.features.histories.mapper.IHistoriesMapper;
import org.nabiha.mobileapi.features.users.UsersEntity;
import org.nabiha.mobileapi.features.users.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HistoriesService implements IHistoriesService{

    private final IHistoriesMapper mapper;
    private final HistoriesRepository repository;
    private final UsersRepository usersRepository;

    @Override
    public List<HistoriesResponseDTO> createHistories(HistoriesRequestBatchDTO historiesRequestBatchDTO) throws ServiceBusinessException {
        List<HistoriesResponseDTO> responseList = new ArrayList<>();
        try {
            for (HistoriesRequestDTO historiesRequestDTO : historiesRequestBatchDTO.getHistories()) {
                HistoriesEntity historiesEntity = mapper.convertToEntity(historiesRequestDTO);
                HistoriesEntity res = repository.save(historiesEntity);
                HistoriesResponseDTO historiesResponseDTO = mapper.convertToDTO(res);
                responseList.add(historiesResponseDTO);
            }
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return responseList;
    }


    @Override
    public List<HistoriesResponseDTO> findAllByUser(String email) throws ServiceBusinessException {
        List<HistoriesResponseDTO> historiesResponseDTOList;
        try {
            UsersEntity users = usersRepository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException("Users not found with email" + email));

            List<HistoriesEntity> historiesEntityList = repository.findAllByUser(users);
            historiesResponseDTOList = historiesEntityList.stream().map(mapper::convertToDTO).toList();
        }catch (Exception ex){
            throw new ServiceBusinessException(ex.getMessage());
        }
        return historiesResponseDTOList;
    }

    @Override
    public HistoriesResponseDTO findById(Long id) throws ServiceBusinessException {
        HistoriesResponseDTO historiesResponseDTO;
        try {
            HistoriesEntity historiesEntity = repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("History not found with id" + id));
            historiesResponseDTO = mapper.convertToDTO(historiesEntity);
        }catch (Exception ex){
            throw new ServiceBusinessException(ex.getMessage());
        }
        return historiesResponseDTO;
    }

    @Override
    public String deleteHistory(Long id) throws ServiceBusinessException {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
        return "History with ID: " + id + " has been deleted";
    }
}
