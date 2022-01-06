package backend.testingonline.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.Question;
import backend.testingonline.model.Test;
import backend.testingonline.responeexception.ResponeObject;

public interface TestService {
	
	List<Test> getAllTest();
	
	Test getWithCode(String code);
	
	List<Test> findByName(String name);

	List<Test> findByLevel(Integer level);
	
	List<Test> findByDone(Integer done);

	List<Test> findBySubject(Integer subject);
	
	ResponseEntity<ResponeObject> deleteById(Integer id);

	Test findById(Integer id);
	
	Test updateTest(Integer id,Test test);
	
	ResponseEntity<ResponeObject> addQuestionTotest(Integer idTest, Integer idQuestion);

	List<Test> findByCandidateId(Integer id);

	ResponseEntity<ResponeObject> addTestForCandidate(Integer idTest, Integer idCandidate);

	void setTestIsDone(Integer id);

	ResponseEntity<ResponeObject> setTestTime(Integer idTest,LocalTime time);
	
	
}
