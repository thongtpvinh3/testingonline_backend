package backend.testingonline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.testingonline.model.Question;
import backend.testingonline.model.Test;
import backend.testingonline.repository.QuestionRepository;
import backend.testingonline.repository.TestRepository;
import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public List<Test> getAllTest() {
		return testRepository.findAll();
	}

	@Override
	public Test getWithCode(String code) {
		return testRepository.findByCodeTest(code);
	}

	@Override
	public List<Test> findByLevel(Integer level) {
		return testRepository.findByLevel(level);
	}

	@Override
	public List<Test> findByDone(Integer done) {
		return testRepository.findByIsDone(done);
	}

	@Override
	public ResponseEntity<ResponeObject> deleteById(Integer id) {
		testRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Delete Success!", ""));
	}

	@Override
	public Test findById(Integer id) {
		return testRepository.getById(id);
	}

	@Override
	public Test updateTest(Integer id, Test test) {
		Test foundTest = testRepository.getById(id);
//		Question newQuestion = questionRepository.getById(id);
//		Question newAddQuestion = test.getQuestions().get(0);
//		foundTest.getQuestions().add(newAddQuestion);

		foundTest.setIdCandidate(test.getIdCandidate());
		foundTest.setIsDone(test.getIsDone());
		foundTest.setLevel(test.getLevel());
		foundTest.setName(test.getName());
		foundTest.setQuestions(test.getQuestions());
		foundTest.setSubject(test.getSubject());
		foundTest.setCodeTest(test.getCodeTest());
		foundTest.setTime(test.getTime());

		return testRepository.save(foundTest);

	}

	@Override
	public ResponseEntity<ResponeObject> addQuestionTotest(Integer idTest, Integer idQuestion) {

		Question newQuestion = questionRepository.getById(idQuestion);
		Test foundTest = testRepository.getById(idTest);
//		while (!foundTest.getQuestions().isEmpty()) {
//			for (Question q : foundTest.getQuestions()) {
//				if (foundTest.getQuestions().contains(q)) {
//					return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
//							.body(new ResponeObject("FAILED", "Duplicate question", ""));
//				} else {
					List<Question> newList = foundTest.getQuestions();
					newList.add(newQuestion);
					foundTest.setQuestions(newList);
					List<Test> newTest = newQuestion.getTests();
					newTest.add(foundTest);
					newQuestion.setTests(newTest);
//				}
//			}
//		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Add success !", testRepository.save(foundTest)));

	}
}
