package backend.testingonline.service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

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
	
//	List<Test> findByDone(Integer done);

	List<Test> findBySubject(Integer subject);
	
	ResponseEntity<ResponeObject> deleteById(Integer id);

	Test findById(Integer id);
	
	Test updateTest(Integer id,Test test);
	
	ResponseEntity<ResponeObject> addQuestionTotest(Integer idTest, Integer idQuestion);

	Set<Test> findByCandidateId(Integer id);

	ResponseEntity<ResponeObject> addTestForCandidate(Integer idTest, Integer idCandidate);

	void setTestIsDone(Integer idTest, Integer idCandidate);

	ResponseEntity<ResponeObject> setTestTime(Integer idTest,LocalTime time);

//	ResponseEntity<ResponeObject> setDateTest(Integer idTest, LocalDateTime dateTest);

	Double reviewMCQuestion(Integer idTest, Integer idCandidate);

	ResponseEntity<ResponeObject> reviewEssayQuestion(Integer idTest,Integer idCandidate, Double mark);
	
	List<Candidate> getCandidateOfTest(Integer idTest);

	Test addNewQuestion(Integer idTest, Question newQuestion);

	List<Question> addQuestionInXlsFile(String xlsFilePath) throws IOException;

}
