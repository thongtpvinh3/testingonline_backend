package backend.testingonline.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.testingonline.model.EssayQuestion;
import backend.testingonline.model.MultipleChoiceQuestion;
import backend.testingonline.model.Question;
import backend.testingonline.model.Test;
import backend.testingonline.repository.EssayQuestionRepository;
import backend.testingonline.repository.MultipleChoiceQuestionRepository;
import backend.testingonline.repository.QuestionRepository;
import backend.testingonline.repository.TestRepository;
import backend.testingonline.responseException.ResponseObject;
import backend.testingonline.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private EssayQuestionRepository essayQuestionRepository;

	@Autowired
	private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

	@Override
	public List<Question> findAll() {
		return questionRepository.findAll();
	}

	@Override
	public ResponseEntity<ResponseObject> save(Question newQuestion) {
		questionRepository.save(newQuestion);
		for (MultipleChoiceQuestion m: newQuestion.getMultipleChoiceQuestions()) {
			m.setQuestion(newQuestion);
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Add success", questionRepository.save(newQuestion)));
	}

	@Override
	public ResponseEntity<ResponseObject> deleteWithId(Integer id) {
		boolean exits = questionRepository.existsById(id);
		if (exits) {
			questionRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Delete Success!", ""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("FAILED", "Cannot find question delete", ""));
	}

	@Override
	public Question findById(Integer id) {
		return questionRepository.findById(id).get();
	}

	@Override
	public Question editQuestion(Integer id, Question newQuestion) {
		Question foundQuestion = questionRepository.getById(id);

		foundQuestion.setContent(newQuestion.getContent());
		foundQuestion.setLevel(newQuestion.getLevel());
		foundQuestion.setSubject(newQuestion.getSubject());
		foundQuestion.setType(newQuestion.getType());
		foundQuestion.setMultipleChoiceQuestions(newQuestion.getMultipleChoiceQuestions());

		return questionRepository.save(foundQuestion);
	}

	@Override
	public Integer getType(Integer idQuestion) {
		return questionRepository.findById(idQuestion).get().getType().getId();
	}

	@Override
	public List<Question> findByType(Integer type) {
		return questionRepository.findByType(type);
	}

	@Override
	public List<Question> findBySubject(Integer subject) {
		return questionRepository.findBySubject(subject);
	}

	@Override
	public List<Question> findByLevel(Integer level) {
		return questionRepository.findByLevel(level);
	}

	@Override
	public Set<Question> getByTestId(Integer idTest) {
		Test foundTest = testRepository.getById(idTest);
		return foundTest.getQuestions();
	}

	@Override
	public ResponseEntity<ResponseObject> addAnswerToQuestion(Integer idAnswer, Integer idQuestion) {
		Question foundQuestion = questionRepository.getById(idQuestion);
		if (foundQuestion.getType().getId() == 1) {
			MultipleChoiceQuestion mq = multipleChoiceQuestionRepository.getById(idAnswer);
			List<MultipleChoiceQuestion> newListMC = foundQuestion.getMultipleChoiceQuestions();
			newListMC.add(mq);
			foundQuestion.setMultipleChoiceQuestions(newListMC);
			mq.setQuestion(foundQuestion);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Add success !", questionRepository.save(foundQuestion)));
		} else {
			EssayQuestion essayQuestion = essayQuestionRepository.getById(idAnswer);
			essayQuestion.setQuestion(foundQuestion);
			foundQuestion.setEssayQuestion(essayQuestion);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Add success !", questionRepository.save(foundQuestion)));
		}
	}

	@Override
	public ResponseEntity<ResponseObject> addMultipleAnswer(Integer idQuestion, MultipleChoiceQuestion ans) {
		Question foundQuestion = questionRepository.getById(idQuestion);
		ans.setQuestion(foundQuestion);
		List<MultipleChoiceQuestion> newList = foundQuestion.getMultipleChoiceQuestions();
		newList.add(ans);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK",
				"Add answer to question id: " + foundQuestion.getId(), questionRepository.save(foundQuestion)));
	}

	@Override
	public ResponseEntity<ResponseObject> addEssayAnswer(Integer idQuestion, EssayQuestion ans) {

		Question foundQuestion = questionRepository.getById(idQuestion);
		ans.setQuestion(foundQuestion);
		foundQuestion.setEssayQuestion(ans);
		essayQuestionRepository.save(ans);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK",
				"Add answer to question id: " + foundQuestion.getId(), questionRepository.save(foundQuestion)));
	}

//	@Override
//	public ResponseEntity<ResponeObject> deleteMultipleAnswerFromQuestion(Integer idAnswer) {
//		return ResponseEntity.status(HttpStatus.OK)
//				.body(new ResponeObject("OK", "Remove success!", multipleChoiceQuestionRepository.deleteById(idAnswer));
//	}

	@Override
	public ResponseEntity<ResponseObject> updateEssayAnswer(Integer idAnswer, EssayQuestion answer) {
		EssayQuestion a = essayQuestionRepository.getById(idAnswer);
		a.setAnswer(answer.getAnswer());
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("", "", essayQuestionRepository.save(answer)));

	}

	@Override
	public ResponseEntity<ResponseObject> updateMCAnswer(Integer idAnswer, MultipleChoiceQuestion answer) {
		MultipleChoiceQuestion a = multipleChoiceQuestionRepository.getById(idAnswer);
		a.setAnswer(answer.getAnswer());
		a.setIsTrue(answer.getIsTrue());
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Update thanh cong!", multipleChoiceQuestionRepository.save(a)));
	}

	@Override
	public ResponseEntity<ResponseObject> deleteMultipleAnswerFromQuestion(Integer idAnswer) {
		multipleChoiceQuestionRepository.deleteById(idAnswer);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Remove success!", ""));
	}

	@Override
	public void removeQuestionFromTest(Integer idQuestion, Integer idTest) {
		Test test = testRepository.getById(idTest);
		Question question = questionRepository.getById(idQuestion);
		
		Set<Question> newList = test.getQuestions();
		newList.remove(question);
		testRepository.save(test);
	}
	
}
