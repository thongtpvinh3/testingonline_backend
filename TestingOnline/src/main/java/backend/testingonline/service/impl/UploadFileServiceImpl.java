package backend.testingonline.service.impl;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import backend.testingonline.service.UploadFileService;

@Service
public class UploadFileServiceImpl implements UploadFileService {

	private final Path storageFolder = Paths.get("uploads");

	public UploadFileServiceImpl() {
		try {
			Files.createDirectories(storageFolder);
		} catch (Exception e) {
			throw new RuntimeException("K the tao thu muc!", e);
		}
	}

	@Override
	public void upload(MultipartFile file) throws Exception {
		try {
			Path destinationFilePath = this.storageFolder.resolve(Paths.get(file.getOriginalFilename())).normalize()
					.toAbsolutePath();
			if (!(destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath()))) {
				throw new RuntimeException("Luu file ngoai!!");
			}

			try(InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			throw new RuntimeException("ko the luu file!", e);
		}
	}

}
