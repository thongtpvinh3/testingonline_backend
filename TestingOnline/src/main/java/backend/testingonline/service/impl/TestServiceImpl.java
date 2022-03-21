package backend.testingonline.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.CandidateTest;
import backend.testingonline.model.Question;
import backend.testingonline.model.TempResultOfCandidate;
import backend.testingonline.model.Test;
import backend.testingonline.repository.CandidateRepository;
import backend.testingonline.repository.CandidateTestRepository;
import backend.testingonline.repository.MultipleChoiceQuestionRepository;
import backend.testingonline.repository.QuestionRepository;
import backend.testingonline.repository.TempResultRepository;
import backend.testingonline.repository.TestRepository;
import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

	@Autowired
	private TempResultRepository tempResultRepository;
	
	@Autowired
	private CandidateTestRepository candidateTestRepository;

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

//	-

	@Override
	public ResponseEntity<ResponeObject> deleteById(Integer id) {
		testRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Delete Success!", ""));
	}

	@Override
	public Test findById(Integer id) {
		return testRepository.findById(id).get();
	}

	@Override
	public Test updateTest(Integer id, Test test) {
		Test foundTest = testRepository.getById(id);

		foundTest.setLevel(test.getLevel());
		foundTest.setName(test.getName());
		foundTest.setQuestions(test.getQuestions());
		foundTest.setSubject(test.getSubject());
		foundTest.setCodeTest(test.getCodeTest());
		foundTest.setTime(test.getTime());
		foundTest.setDateTest(test.getDateTest());

		return testRepository.save(foundTest);

	}

	@Override
	public ResponseEntity<ResponeObject> addQuestionTotest(Integer idTest, Integer idQuestion) {

		Question newQuestion = questionRepository.findById(idQuestion).get();
		Test foundTest = testRepository.getById(idTest);

		if (foundTest.getQuestions().size() == 0 && newQuestion.getLevel() == foundTest.getLevel()
				&& newQuestion.getSubject() == foundTest.getSubject()) {
			Set<Question> newList = foundTest.getQuestions();
			newList.add(newQuestion);
			foundTest.setQuestions(newList);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponeObject("OK", "Add success !", testRepository.save(foundTest)));
		} else {
			if (newQuestion.getLevel() != foundTest.getLevel() || newQuestion.getSubject() != foundTest.getSubject()) {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
						.body(new ResponeObject("FAILED", "Subject or Level is not equals", ""));
			}
			if (foundTest.getQuestions().contains(newQuestion) == false
					&& newQuestion.getLevel() == foundTest.getLevel()
					&& newQuestion.getSubject() == foundTest.getSubject()) {
				Set<Question> newList = foundTest.getQuestions();
				newList.add(newQuestion);
				foundTest.setQuestions(newList);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponeObject("OK", "Add success !", testRepository.save(foundTest)));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
						.body(new ResponeObject("FAILED", "Duplicate question", ""));
			}
		}
	}

	@Override
	public List<Test> findByName(String name) {
		return testRepository.findByName(name);
	}

	@Override
	public List<Test> findBySubject(Integer subject) {
		return testRepository.findBySubject(subject);
	}

	@Override
	public Set<Test> findByCandidateId(Integer id) {
		Candidate foundCandidate = candidateRepository.getById(id);
		return foundCandidate.getTests();
	}

	@Override
	public ResponseEntity<ResponeObject> addTestForCandidate(Integer idTest, Integer idCandidate) {
		Test newTest = testRepository.getById(idTest);
		Candidate foundCandidate = candidateRepository.getById(idCandidate);

		if (newTest.getLevel() != foundCandidate.getLevel()) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("FAILED", "Level khong phu hop!", ""));
		}

		Set<Test> newList = foundCandidate.getTests();
//		for (int i = 0; i< newList.size();i++) {
//			System.out.println("\n\n\n"+newList..toString1()+"\n\n\n");
//		}
		if (foundCandidate.getTests().contains(newTest) == false) {
//			foundCandidate.setTests(null);
			newList.add(newTest);
			foundCandidate.setTests(newList);
//			for (int i1 = 0; i1< newList.size();i1++) {
//				System.out.println("\n\n\n"+newList.get(i1).toString1()+"\n\n\n");
//			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponeObject("OK", "Add success !", candidateRepository.save(foundCandidate)));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponeObject("FAILED", "Bai test bi trung !", ""));
		}
	}

	@Override
	public void setTestIsDone(Integer idTest, Integer idCandidate) {
		candidateTestRepository.setIsDone(idTest,idCandidate);
	}

	@Override
	public ResponseEntity<ResponeObject> setTestTime(Integer idTest, LocalTime time) {
		Test foundTest = testRepository.getById(idTest);
		foundTest.setTime(time);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Set test time success!", testRepository.save(foundTest)));
	}

	@Override
	public ResponseEntity<ResponeObject> setDateTest(Integer idTest, LocalDateTime dateTest) {
		Test foundTest = testRepository.getById(idTest);
		if (dateTest.isAfter(LocalDateTime.now())) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponeObject("FAILED", "Thoi gian khong hop le!", ""));
		} else {
			foundTest.setDateTest(dateTest);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponeObject("OK", "Set date test success!", testRepository.save(foundTest)));
		}
	}

	@Override
	public Double reviewMCQuestion(Integer idTest, Integer idCandidate) {
		Test foundTest = testRepository.getById(idTest);
		Set<Question> thisTestQuestion = foundTest.getQuestions();
//		Integer idCandidate = foundTest.getCandidate().getId();
		List<TempResultOfCandidate> result = tempResultRepository.getAnswerOfCandidate(idCandidate,0);
		int count = 0;
		int rightResult = 0;

		for (Question q : thisTestQuestion) {
			if (q.getType() == 0) {
				count++;
			}
		}
		for (TempResultOfCandidate res : result) {
			if (multipleChoiceQuestionRepository.findWithIdAndisTrue(res.getIdAnswer(),
					Integer.parseInt(res.getAnswer())) != null) {
				rightResult++;
			}
		}

		Double oneQuestionMark = 50.0/count;
		DecimalFormat df = new DecimalFormat("#.##");
		Double tmp = Double.parseDouble(df.format(oneQuestionMark));
		
		Double lastResult = tmp * rightResult;
		CandidateTest candidateTest = candidateTestRepository.findByCandidateIdAndTestId(idCandidate,idTest);
		candidateTest.setMarks(lastResult+candidateTest.getMarks());
		candidateTestRepository.save(candidateTest);
//		foundTest.setMarks(lastResult);
		testRepository.save(foundTest);

		return lastResult;
	}

	@Override
	public ResponseEntity<ResponeObject> reviewEssayQuestion(Integer idTest,Integer idCandidate,Double mark) {
		Test foundTest = testRepository.getById(idTest);
		Set<Question> listQ = foundTest.getQuestions();
		CandidateTest candidateTest = candidateTestRepository.findByCandidateIdAndTestId(idCandidate, idTest);
		int count = 0;
		for (Question q: listQ) {
			if(q.getType() == 1) {
				count++;
			}
		}
		
		Double oneQuestionMark = 50.0/count;
		DecimalFormat df = new DecimalFormat("#.##");
		Double tmp = Double.parseDouble(df.format(oneQuestionMark));
		
		if(mark>tmp) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
					new ResponeObject("FAILED!", "Diem vuot qua max cau hoi! (" + tmp + ")", "")
					);
		} else {
//			EssayQuestion e = essayQuestionRepository.getById(idEssay);
//			e.setMark(mark);
//			Double x = foundTest.getMarks()+mark;
			Double x = candidateTest.getMarks()+mark;
			candidateTest.setMarks(x);
			candidateTestRepository.save(candidateTest);
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponeObject("OK", "Cham Diem thanh cong!", testRepository.save(foundTest))
					);
		}
	}

	@Override
	public List<Candidate> getCandidateOfTest(Integer idTest) {
		return testRepository.getById(idTest).getCandidates();
	}

}
