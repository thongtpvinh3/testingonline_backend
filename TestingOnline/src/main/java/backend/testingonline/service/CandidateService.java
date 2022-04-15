package backend.testingonline.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.Test;
import backend.testingonline.responeexception.ResponeObject;

public interface CandidateService {

	List<Candidate> findByEmail(String email);

	List<Candidate> findByPhone(String phone);
	
	List<Candidate> findAll(); 
	
	Candidate findById(Integer id);

	ResponseEntity<ResponeObject> save(Candidate newCandidate);
	
	ResponseEntity<ResponeObject> deleteWithId(Integer id);
	
	ResponseEntity<ResponeObject> setMark(Integer id);

	Test joinTestByCode(String code, Integer idCandidate);
	
	Set<Test> joinAllTest(Integer idCandidate);

	void setIsDone(int i);

	ResponseEntity<ResponeObject> fixIsDone(Integer idCandidate);
}
