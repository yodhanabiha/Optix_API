package org.nabiha.mobileapi.services;

import org.nabiha.mobileapi.config.FileStorageProperties;
import org.nabiha.mobileapi.exception.ServiceBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;

@Service
public class FileStorageService implements IFileStorageService {

    private final Path productFileStorageLocation;
    private final Path userFileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.productFileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        this.userFileStorageLocation = Paths.get(fileStorageProperties.getUserUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.productFileStorageLocation);
            Files.createDirectories(this.userFileStorageLocation);
        } catch (Exception ex) {
            throw new ServiceBusinessException(ex.getMessage());
        }
    }

    @Override
    public String storeFile(MultipartFile file, String fileType) {
        String uniqueName = "file_" + Instant.now().toEpochMilli() + "_";
        String fileName = StringUtils.cleanPath(uniqueName+file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new ServiceBusinessException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation;
            String fileUrlPrefix;

            if ("USER".equalsIgnoreCase(fileType)) {
                targetLocation = this.userFileStorageLocation.resolve(fileName);
                fileUrlPrefix = "/images_user/";
            } else if ("PRODUCT".equalsIgnoreCase(fileType)) {
                targetLocation = this.productFileStorageLocation.resolve(fileName);
                fileUrlPrefix = "/images_product/";
            } else {
                throw new ServiceBusinessException("Invalid file type specified: " + fileType);
            }

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileUrlPrefix + fileName;
        } catch (IOException ex) {
            throw new ServiceBusinessException("Could not store file " + fileName + ". Please try again!");
        }
    }
}

