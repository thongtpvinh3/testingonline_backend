package backend.testingonline.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

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
import backend.testingonline.responseException.ResponseObject;
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
	public ResponseEntity<ResponseObject> save(Candidate newCandidate) {

		List<Candidate> foundCandidateEmail = candidateRepository.findByEmail(newCandidate.getEmail());
		List<Candidate> foundCandidatePhone = candidateRepository.findByPhone(newCandidate.getPhone());
		if (foundCandidateEmail.size() > 0 || foundCandidatePhone.size() > 0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("FAILED", "Email or Phone duplicate", ""));
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
				.body(new ResponseObject("OK", "Add success", candidateRepository.save(newCandidate)));
//		} catch (Exception e) {
//			throw new RuntimeException("FAILED",e);
//		}
	}
	
	@Override
	public Set<Test> joinAllTest(Integer idCandidate) {
		Candidate candidate = candidateRepository.getById(idCandidate);
		Set<Test> listTest = candidate.getTests();
		int timenow = LocalDateTime.now().toLocalTime().toSecondOfDay();
		int timeTest = 0;
		
		for (Test t: listTest) {
			timeTest += t.timeToSecond();
		}
		int timeStart = candidate.getDates().toLocalTime().toSecondOfDay();
		
		if(candidate.getIsDone() == 0) {
//			if(candidate.getDates().toLocalDate().equals(LocalDate.now()) == false) {
//				System.out.println("Chua den hoac da qua ngay test!");
//				return null;
//			}
//			if (candidate.getDates().toLocalTime().isAfter(LocalTime.now())) {
				System.out.print(candidate.getDates().toLocalTime().isAfter(LocalTime.now()));
				System.out.println(candidate.getDates().toLocalTime());
//				System.out.println("chua den h!");
//				return null;
//			}
//			if(timenow-timeStart>timeTest) {
//				System.out.println("Da het gio lam bai");
//				return null;
//			}
			return listTest;
		}
		System.out.println("Bai thi da lam xong!");
		return null;
	}


	@Override
	public Test joinTestByCode(String code, Integer idCandidate) {
//		Test optionalTest = testRepository.findByCodeTest(code);
//		
//		if (optionalTest != null) {
//			Integer idTest = optionalTest.getId();
//			CandidateTest candidateTest = candidateTestRepository.findByCandidateIdAndTestId(idCandidate, idTest);
//			int timeNow = LocalDateTime.now().toLocalTime().toSecondOfDay();
//			int timeTest = optionalTest.timeToSecond();
//			int timeStart = optionalTest.getDateTest().toLocalTime().toSecondOfDay();
//
//			if (candidateTest.getIsDone() == 0) {
//				if (optionalTest.getDateTest().toLocalDate().equals(LocalDate.now()) == false) {
//					System.out.println("Chua den ngay hoac da qua ngay test");
//					return null;
//				}
//				if (optionalTest.getDateTest().toLocalTime().isAfter(LocalTime.now())) {
//					System.out.println("Chua den gio");
//					return null;
//				}
//				if (timeNow - timeStart > timeTest) {
//					System.out.println("Da het thoi gian lam bai");
//					return null;
//				}
//				return optionalTest;
//			} else {
//				System.out.println("Bai Thi Da Lam xong");
//				return null;
//			}
//		}
		return null;
	}

	@Override
	public ResponseEntity<ResponseObject> deleteWithId(Integer id) {
		boolean exits = candidateRepository.existsById(id);
		if (exits) {
			candidateRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Delete Success!", ""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("FAILED", "Cannot find candidate delete", ""));
	}

	@Override
	public Candidate findById(Integer id) {
		return candidateRepository.findById(id).get();
	}

	@Override
	public ResponseEntity<ResponseObject> setMark(Integer idCandidate) {
		Candidate foundCandidate = candidateRepository.getById(idCandidate);
		
		for (CandidateTest ct: candidateTestRepository.findByCandidateId(idCandidate)) {
			Double marks = ct.getMarks();
			if(marks == null) ct.setMarks(0.0);
			switch (testRepository.getById(ct.getTestId()).getSubject().getId()) {
			case 1:
				foundCandidate.setEnglishMark(ct.getMarks());
				break;
			case 2:
				foundCandidate.setCodingMark(ct.getMarks());
				break;
			case 3:
				foundCandidate.setKnowledgeMark(ct.getMarks());
				break;
			default:
				break;
			}
			
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Set diem thanh cong", candidateRepository.save(foundCandidate)));
	}

	@Override
	public void setIsDone(int idCandidate) {
		candidateRepository.setIsDone(idCandidate);
		
	}

	@Override
	public ResponseEntity<ResponseObject> fixIsDone(Integer idCandidate) {
		return candidateRepository.fixIsDone(idCandidate);
	}

}
