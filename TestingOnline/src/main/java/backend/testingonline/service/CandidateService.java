package backend.testingonline.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Candidate;
import backend.testingonline.responeexception.ResponeObject;

public interface CandidateService {

	List<Candidate> findByEmail(String email);

	List<Candidate> findByPhone(String phone);
	
	List<Candidate> findAll(); 

	ResponseEntity<ResponeObject> save(Candidate newCandidate);
	
	boolean joinTestByCode(String code);

	ResponseEntity<ResponeObject> deleteWithId(Integer id);
	
}
