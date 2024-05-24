package org.nabiha.mobileapi.services;

import org.nabiha.mobileapi.config.FileStorageProperties;
import org.nabiha.mobileapi.exception.ServiceBusinessException;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {

    String storeFile(MultipartFile file, String fileType) throws ServiceBusinessException;

}
