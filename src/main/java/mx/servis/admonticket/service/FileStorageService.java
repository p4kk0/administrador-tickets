package mx.servis.admonticket.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

	String storeFile(MultipartFile file, String rootPath, String fileName);

	Resource loadFileAsResource(String rootPath, String fileName);

}
