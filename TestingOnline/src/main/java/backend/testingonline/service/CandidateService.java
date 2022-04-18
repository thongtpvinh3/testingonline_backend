package backend.testingonline.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.Test;
import backend.testingonline.responseException.ResponseObject;

public interface CandidateService {

	List<Candidate> findByEmail(String email);

	List<Candidate> findByPhone(String phone);
	
	List<Candidate> findAll(); 
	
	Candidate findById(Integer id);

	ResponseEntity<ResponseObject> save(Candidate newCandidate);
	
	ResponseEntity<ResponseObject> deleteWithId(Integer id);
	
	ResponseEntity<ResponseObject> setMark(Integer id);

	Test joinTestByCode(String code, Integer idCandidate);
	
	Set<Test> joinAllTest(Integer idCandidate);

	void setIsDone(int i);

	ResponseEntity<ResponseObject> fixIsDone(Integer idCandidate);
}
