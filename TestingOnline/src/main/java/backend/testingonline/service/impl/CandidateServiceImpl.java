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
import backend.testingonline.model.Test;
import backend.testingonline.repository.CandidateRepository;
import backend.testingonline.repository.TestRepository;
import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService { 

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private TestRepository testRepository;

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

	@Override
	public ResponseEntity<ResponeObject> save(Candidate newCandidate) {

		List<Candidate> foundCandidateEmail = candidateRepository.findByEmail(newCandidate.getEmail());
		List<Candidate> foundCandidatePhone = candidateRepository.findByPhone(newCandidate.getPhone());
		if (foundCandidateEmail.size() > 0 || foundCandidatePhone.size() > 0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponeObject("FAILED", "Email or Phone duplicate", ""));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Add success", candidateRepository.save(newCandidate)));
	}

	@Override
	public boolean joinTestByCode(String code) {
		Test optionalTest = testRepository.findByCodeTest(code);
		
		if (optionalTest != null) {
			int timeNow = LocalDateTime.now().toLocalTime().toSecondOfDay();
			int timeTest = optionalTest.timeToSecond();
			int timeStart = optionalTest.getDateTest().toLocalTime().toSecondOfDay();
			
			if (optionalTest.getIsDone() == 0) {
				if (optionalTest.getDateTest().toLocalDate().equals(LocalDate.now()) == false) {System.out.println("Chua den ngay hoac da qua ngay test"); return false;}
				if (optionalTest.getDateTest().toLocalTime().isAfter(LocalTime.now())) { System.out.println("Chua den gio"); return false;}
				if (timeNow-timeStart > timeTest) {System.out.println("Da het thoi gian lam bai"); return false;}
				return true;
			} else {
				System.out.println("Bai Thi Da Lam xong");
				return false;
			}
		}
		return false;
	}

	@Override
	public ResponseEntity<ResponeObject> deleteWithId(Integer id) {
		boolean exits = candidateRepository.existsById(id);
		if (exits) {
			candidateRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Delete Success!", ""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponeObject("FAILED","Cannot find candidate delete","")
				);
	}

	@Override
	public Candidate findById(Integer id) {
		return candidateRepository.getById(id);
	}

	@Override
	public ResponseEntity<ResponeObject> setMark(Integer id) {
		Candidate foundCandidate = candidateRepository.getById(id);
		List<Test> foundTest = foundCandidate.getTests();
		for (Test t: foundTest) {
			if (t.getSubject() == 1) {
				foundCandidate.setEnglishMark(t.getMarks());
			}
			if (t.getSubject() == 2) {
				foundCandidate.setCodingMark(t.getMarks());
			}
			if (t.getSubject() == 3) {
				foundCandidate.setKnowledgeMark(t.getMarks());
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponeObject("OK","Set diem thanh cong", candidateRepository.save(foundCandidate))
				);
	}
	
	
}
