package backend.testingonline.service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.DateCandidate;
import backend.testingonline.model.Levels;
import backend.testingonline.model.Question;
import backend.testingonline.model.Subject;
import backend.testingonline.model.Test;
import backend.testingonline.responseException.ResponseObject;

public interface TestService {
	
	List<Test> getAllTest();
	
	List<Test> findByName(String name);

	List<Test> findByLevel(Levels level);
	
	List<Test> findBySubject(Subject subject);
	
	ResponseEntity<ResponseObject> deleteById(Integer id);

	Test findById(Integer id);
	
	Test updateTest(Integer id,Test test);
	
	ResponseEntity<ResponseObject> addQuestionTotest(Integer idTest, Integer idQuestion);

	Set<Test> findByCandidateId(Integer id);

	ResponseEntity<ResponseObject> addTestForCandidate(Integer idTest, Integer idCandidate);

	Double reviewMCQuestion(Integer idTest, Integer idCandidate);

	ResponseEntity<ResponseObject> reviewEssayQuestion(Integer idTest,Integer idCandidate, Double mark);
	
	Test addNewQuestion(Integer idTest, Question newQuestion);

	List<Question> addQuestionInXlsFile(String xlsFilePath) throws IOException;

	Set<DateCandidate> getOutOfDateTest();
	
	DateCandidate getTodayTest();

	Set<DateCandidate> getUndueTest();

	List<Candidate> getOutOfDateCandidate();
	
	List<Candidate> getTodayCandidate();
	
	List<Candidate> getUndueCandidate();
}
