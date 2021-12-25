package backend.testingonline.service.impl;

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
		List<Candidate> foundCandidatePhone = candidateRepository.findByEmail(newCandidate.getPhone());
		if (foundCandidateEmail.size() > 0 || foundCandidatePhone.size() > 0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
					new ResponeObject("FAILED","Email or Phone duplicate","")
					);
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponeObject("OK","Add success", candidateRepository.save(newCandidate))
				);
	}

	@Override
	public boolean joinTestByCode(String code) {
		Test optionalTest = testRepository.findByCodeTest(code);
		if (optionalTest != null) {
			System.out.println(optionalTest.toString());
			return true;
		}
		return false;
	}
}
