package backend.testingonline.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
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
			if (file.isEmpty()) {
				throw new RuntimeException("File must not be empty");
			}
			Path destinationFilePath = this.storageFolder.resolve(Paths.get(file.getOriginalFilename())).normalize()
					.toAbsolutePath();
			if (!(destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath()))) {
				throw new RuntimeException("Luu file ngoai!!");
			}

			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			throw new RuntimeException("ko the luu file!", e);
		}
	}

	private boolean isImageFile(MultipartFile file) {
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		return Arrays.asList(new String[] { "png", "jpg", "jpeg", "bmp" }).contains(fileExtension.trim().toLowerCase());
	}

	@Override
	public String uploadAvatar(MultipartFile file) throws Exception {
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed");
			}
			if (!isImageFile(file)) {
				throw new RuntimeException("Upload not a image file");
			}

			float fileSizeMB = file.getSize() / 1_000_000.0f;
			if (fileSizeMB > 5.0f) {
				throw new RuntimeException("File must be < 5MB");
			}

			String fileExtesion = FilenameUtils.getExtension(file.getOriginalFilename());
			String genNameFile = UUID.randomUUID().toString().replace("-", "");
			genNameFile = genNameFile + "." + fileExtesion;
			Path destinationFilePath = this.storageFolder.resolve(Paths.get(genNameFile)).normalize().toAbsolutePath();
			if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
				throw new RuntimeException("Cannot store file outside current directory");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			}

			return genNameFile;
		} catch (Exception e) {
			throw new RuntimeException("Failed to store file", e);
		}
	}

	@Override
	public byte[] readImgContent(String filename) {
		Path file = storageFolder.resolve(filename);
		Resource resource;
		try {
			resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return StreamUtils.copyToByteArray(resource.getInputStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("Can't read file"+filename,e);
		}
		return new byte[0];
	}

}
