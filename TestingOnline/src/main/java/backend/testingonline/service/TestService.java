package backend.testingonline.service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.Levels;
import backend.testingonline.model.Question;
import backend.testingonline.model.Subject;
import backend.testingonline.model.Test;
import backend.testingonline.responseException.ResponseObject;

public interface TestService {
	
	List<Test> getAllTest();
	
	Test getWithCode(String code);
	
	List<Test> findByName(String name);

	List<Test> findByLevel(Levels level);
	
	List<Test> findBySubject(Subject subject);
	
	ResponseEntity<ResponseObject> deleteById(Integer id);

	Test findById(Integer id);
	
	Test updateTest(Integer id,Test test);
	
	ResponseEntity<ResponseObject> addQuestionTotest(Integer idTest, Integer idQuestion);

	Set<Test> findByCandidateId(Integer id);

	ResponseEntity<ResponseObject> addTestForCandidate(Integer idTest, Integer idCandidate);

	void setTestIsDone(Integer idTest, Integer idCandidate);

	ResponseEntity<ResponseObject> setTestTime(Integer idTest,LocalTime time);

//	ResponseEntity<ResponeObject> setDateTest(Integer idTest, LocalDateTime dateTest);

	Double reviewMCQuestion(Integer idTest, Integer idCandidate);

	ResponseEntity<ResponseObject> reviewEssayQuestion(Integer idTest,Integer idCandidate, Double mark);
	
	List<Candidate> getCandidateOfTest(Integer idTest);

	Test addNewQuestion(Integer idTest, Question newQuestion);

	List<Question> addQuestionInXlsFile(String xlsFilePath) throws IOException;

}
