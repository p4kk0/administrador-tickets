package mx.servis.admonticket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import mx.servis.admonticket.property.FileStorageProperties;
import mx.servis.admonticket.rest.error.FileStorageException;
import mx.servis.admonticket.rest.error.MyFileNotFoundException;
import mx.servis.admonticket.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService{
	
	private Path fileStorageLocation;

//    @Autowired
//    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
////        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
////                .toAbsolutePath().normalize();
//
//        try {
//            Files.createDirectories(this.fileStorageLocation);
//        } catch (Exception ex) {
//            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
//        }
//    }
    
    public String storeFile(MultipartFile file, String rootPath, String fileName) {
    	
    	 this.fileStorageLocation = Paths.get(rootPath).toAbsolutePath().normalize();
    	 
    	 try {
             Files.createDirectories(this.fileStorageLocation);
         } catch (Exception ex) {
             throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
         }
        // Normalize file name
        String fileNamePath = StringUtils.cleanPath(fileName);

        try {
            // Check if the file's name contains invalid characters
            if(fileNamePath.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileNamePath);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileNamePath);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileNamePath;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileNamePath + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String rootPath, String fileName) {
    	
    	this.fileStorageLocation = Paths.get(rootPath).toAbsolutePath().normalize();
    	
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

}
