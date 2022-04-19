package backend.testingonline.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.QuestionType;
import backend.testingonline.model.Staff;
import backend.testingonline.model.Test;
import backend.testingonline.responseException.ResponseObject;

public interface StaffService {
	
	Staff findByUsernameAndPassword(String username, String password);
	
	boolean login(String username, String password);
	
	Optional<Candidate> findById(int id);
	
	ResponseEntity<ResponseObject> createTest(Test test);
	
	ResponseEntity<ResponseObject> creaeteType(QuestionType questionType);
	
}
