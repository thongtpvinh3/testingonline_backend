package backend.testingonline.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {

	void upload(MultipartFile file) throws Exception;

}
