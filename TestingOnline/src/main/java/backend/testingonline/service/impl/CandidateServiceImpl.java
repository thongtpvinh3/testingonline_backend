package backend.testingonline.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.CandidateTest;
import backend.testingonline.model.Test;
import backend.testingonline.repository.CandidateRepository;
import backend.testingonline.repository.CandidateTestRepository;
import backend.testingonline.repository.TestRepository;
import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private CandidateTestRepository candidateTestRepository;

	@Override
	public List<Candidate> findByEmail(String email) {
		return candidateRepository.findByEmail(email);
	}

	@Override
	public List<Candidate> findByPhone(String phone) {
		return candidateRepository.findByPhone(phone);
	}

	@Override
	public List<Candidate> findAll() {
		return candidateRepository.findAll();
	}

//	private final Path storageFolder = Paths.get("uploads");
//
//	private boolean isImageFile(MultipartFile file) {
//		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
//		return Arrays.asList(new String[] { "png", "jpg", "jpeg", "bmp" }).contains(fileExtension.trim().toLowerCase());
//	}

	@Override
	public ResponseEntity<ResponeObject> save(Candidate newCandidate) {

		List<Candidate> foundCandidateEmail = candidateRepository.findByEmail(newCandidate.getEmail());
		List<Candidate> foundCandidatePhone = candidateRepository.findByPhone(newCandidate.getPhone());
		if (foundCandidateEmail.size() > 0 || foundCandidatePhone.size() > 0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponeObject("FAILED", "Email or Phone duplicate", ""));
		}

//		try {
//			System.out.println("\n\n\n ------------- \n\n");
//			if(multipartFile.isEmpty()) {
//				throw new RuntimeException("Failed to store empty file");
//			}
//			
//			if (!isImageFile(multipartFile)) {
//				throw new RuntimeException("Not a image file");
//			}
//			
//			float fileSizeMB = multipartFile.getSize() / 1_000_000.0f;
//			if(fileSizeMB > 5.0f) {
//				throw new RuntimeException("File must be < 5MB");
//			}
//			
//			//Lay duoi file
//			String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
//			String generatedFileName = UUID.randomUUID().toString().replace("-", "");
//			Path destinationFilePath = this.storageFolder.resolve(Paths.get(generatedFileName)).normalize().toAbsolutePath();
//			if(!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath()) ) {
//				throw new RuntimeException("Cannot store file outside current directory");
//			}
//			try(InputStream inputStream = multipartFile.getInputStream()) {
//				Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
//			}
//			
//			newCandidate.setAvatar(generatedFileName);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Add success", candidateRepository.save(newCandidate)));
//		} catch (Exception e) {
//			throw new RuntimeException("FAILED",e);
//		}
	}

	@Override
	public Test joinTestByCode(String code, Integer idCandidate) {
		Test optionalTest = testRepository.findByCodeTest(code);
		
		if (optionalTest != null) {
			Integer idTest = optionalTest.getId();
			CandidateTest candidateTest = candidateTestRepository.findByCandidateIdAndTestId(idCandidate, idTest);
			int timeNow = LocalDateTime.now().toLocalTime().toSecondOfDay();
			int timeTest = optionalTest.timeToSecond();
			int timeStart = optionalTest.getDateTest().toLocalTime().toSecondOfDay();

			if (candidateTest.getIsDone() == 0) {
				if (optionalTest.getDateTest().toLocalDate().equals(LocalDate.now()) == false) {
					System.out.println("Chua den ngay hoac da qua ngay test");
					return null;
				}
				if (optionalTest.getDateTest().toLocalTime().isAfter(LocalTime.now())) {
					System.out.println("Chua den gio");
					return null;
				}
				if (timeNow - timeStart > timeTest) {
					System.out.println("Da het thoi gian lam bai");
					return null;
				}
				return optionalTest;
			} else {
				System.out.println("Bai Thi Da Lam xong");
				return null;
			}
		}
		return null;
	}

	@Override
	public ResponseEntity<ResponeObject> deleteWithId(Integer id) {
		boolean exits = candidateRepository.existsById(id);
		if (exits) {
			candidateRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Delete Success!", ""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponeObject("FAILED", "Cannot find candidate delete", ""));
	}

	@Override
	public Candidate findById(Integer id) {
		return candidateRepository.findById(id).get();
	}

	@Override
	public ResponseEntity<ResponeObject> setMark(Integer id) {
		Candidate foundCandidate = candidateRepository.getById(id);
		
		for (CandidateTest ct: candidateTestRepository.findByCandidateId(id)) {
//			Double marks = ct.getMarks();
//			if(marks.isEmpty()) ct.setMarks(0.0);
			switch (testRepository.getById(ct.getTestId()).getSubject()) {
			case 1:
				foundCandidate.setEnglishMark(ct.getMarks());
				break;
			case 2:
				foundCandidate.setCodingMark(ct.getMarks());
				break;
			case 3:
				foundCandidate.setKnowledgeMark(ct.getMarks());
			default:
				break;
			}
			
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Set diem thanh cong", candidateRepository.save(foundCandidate)));
	}

}
