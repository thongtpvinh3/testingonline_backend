package backend.testingonline.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import backend.testingonline.model.Candidate;
import backend.testingonline.responeexception.ResponeObject;

public interface CandidateService {

	List<Candidate> findByEmail(String email);

	List<Candidate> findByPhone(String phone);
	
	List<Candidate> findAll(); 
	
	Candidate findById(Integer id);

	ResponseEntity<ResponeObject> save(Candidate newCandidate);
	
	boolean joinTestByCode(String code);

	ResponseEntity<ResponeObject> deleteWithId(Integer id);
	
	ResponseEntity<ResponeObject> setMark(Integer id);

	
}
