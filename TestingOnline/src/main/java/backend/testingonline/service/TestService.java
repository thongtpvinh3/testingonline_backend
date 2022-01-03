package backend.testingonline.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Question;
import backend.testingonline.model.Test;
import backend.testingonline.responeexception.ResponeObject;

public interface TestService {
	
	List<Test> getAllTest();
	
	Test getWithCode(String code);

	List<Test> findByLevel(Integer level);
	
	List<Test> findByDone(Integer done);

	ResponseEntity<ResponeObject> deleteById(Integer id);

	Test findById(Integer id);
	
	Test updateTest(Integer id,Test test);
	
	ResponseEntity<ResponeObject> addQuestionTotest(Integer idTest, Integer idQuestion);
}
