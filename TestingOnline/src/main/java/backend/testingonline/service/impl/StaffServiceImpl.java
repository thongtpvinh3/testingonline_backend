package backend.testingonline.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.Staff;
import backend.testingonline.model.Test;
import backend.testingonline.repository.CandidateRepository;
import backend.testingonline.repository.QuestionRepository;
import backend.testingonline.repository.StaffRepository;
import backend.testingonline.repository.TestRepository;
import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private TestRepository testRepository;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Optional<Candidate> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Staff findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return staffRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		Staff optionalStaff = staffRepository.findByUsernameAndPassword(username, password);
		if (optionalStaff != null && optionalStaff.getPassword().equals(password)) {
			System.out.println(optionalStaff.toString());
			return true;
		}
		return false;
	}
	

	@Override
	public ResponseEntity<ResponeObject> createTest(Test newTest) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Add success!", testRepository.save(newTest)));
	}
}
