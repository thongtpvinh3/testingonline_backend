package backend.testingonline.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.Question;
import backend.testingonline.model.Staff;
import backend.testingonline.model.Test;
import backend.testingonline.responeexception.ResponeObject;

public interface StaffService {
	
	Staff findByUsernameAndPassword(String username, String password);
	
	boolean login(String username, String password);
	
	Optional<Candidate> findById(int id);
	
	ResponseEntity<ResponeObject> createTest(Test test);
	
//	Test addQuestionToTest(int idTest, Question newQuestion);
	
}
