package backend.testingonline.service.impl;

import java.util.List;

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
import backend.testingonline.responeexception.ResponeObject;
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
	public ResponseEntity<ResponeObject> save(Question newQuestion) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Add success", questionRepository.save(newQuestion)));
	}

	@Override
	public ResponseEntity<ResponeObject> deleteById(Integer id) {
		try {
			questionRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println("noooo!");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponeObject("NO!!!", "Delete K noi!", ""));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Delete Success!", ""));
	}

	@Override
	public Question findById(Integer id) {
		return questionRepository.findById(id).get();
	}

	@Override
	public Question editQuestion(Integer id, Question newQuestion) {
		Question foundQuestion = questionRepository.getById(id);

//		foundQuestion.setId(newQuestion.getId());
		foundQuestion.setContent(newQuestion.getContent());
		foundQuestion.setLevel(newQuestion.getLevel());
		foundQuestion.setSubject(newQuestion.getSubject());
		foundQuestion.setType(newQuestion.getType());

		return questionRepository.save(foundQuestion);
	}

	@Override
	public Integer getType(Integer idQuestion) {
		return questionRepository.findById(idQuestion).get().getType();
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
	public List<Question> getByTestId(Integer idTest) {
		Test foundTest = testRepository.getById(idTest);
		return foundTest.getQuestions();
	}

	@Override
	public ResponseEntity<ResponeObject> addAnswerToQuestion(Integer idAnswer, Integer idQuestion) {
		Question foundQuestion = questionRepository.getById(idQuestion);
		if (foundQuestion.getType() == 0) {
			MultipleChoiceQuestion mq = multipleChoiceQuestionRepository.getById(idAnswer);
			List<MultipleChoiceQuestion> newListMC = foundQuestion.getMultipleChoiceQuestions();
			newListMC.add(mq);
			foundQuestion.setMultipleChoiceQuestions(newListMC);
			mq.setQuestion(foundQuestion);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponeObject("OK", "Add success !", questionRepository.save(foundQuestion)));
		} else {
			EssayQuestion essayQuestion = essayQuestionRepository.getById(idAnswer);
			essayQuestion.setQuestion(foundQuestion);
			foundQuestion.setEssayQuestion(essayQuestion);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponeObject("OK", "Add success !", questionRepository.save(foundQuestion)));
		}
	}

	@Override
	public ResponseEntity<ResponeObject> addMultipleAnswer(Integer idQuestion, MultipleChoiceQuestion ans) {
		Question foundQuestion = questionRepository.getById(idQuestion);
		ans.setQuestion(foundQuestion);
		List<MultipleChoiceQuestion> newList = foundQuestion.getMultipleChoiceQuestions();
		newList.add(ans);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK",
				"Add answer to question id: " + foundQuestion.getId(), questionRepository.save(foundQuestion)));
	}

	@Override
	public ResponseEntity<ResponeObject> addEssayAnswer(Integer idQuestion, EssayQuestion ans) {

		Question foundQuestion = questionRepository.getById(idQuestion);
		ans.setQuestion(foundQuestion);
		foundQuestion.setEssayQuestion(ans);
		essayQuestionRepository.save(ans);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK",
				"Add answer to question id: " + foundQuestion.getId(), questionRepository.save(foundQuestion)));
	}

	@Override
	public ResponseEntity<ResponeObject> deleteMultipleAnswerFromQuestion(Integer idQuestion, Integer idAnswer) {
		Question question = questionRepository.getById(idQuestion);
		List<MultipleChoiceQuestion> thisListAnswer = question.getMultipleChoiceQuestions();
		thisListAnswer.remove(multipleChoiceQuestionRepository.getById(idAnswer));
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Remove success!", questionRepository.save(question)));
	}

	@Override
	public ResponseEntity<ResponeObject> updateEssayAnswer(Integer idQuestion, EssayQuestion answer) {
		Question question = questionRepository.getById(idQuestion);
		essayQuestionRepository.save(answer);
		question.setEssayQuestion(answer);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("", "", questionRepository.save(question)));

	}

	@Override
	public ResponseEntity<ResponeObject> updateMCAnswer(Integer idQuestion, MultipleChoiceQuestion answer) {
		Question question = questionRepository.getById(idQuestion);
		multipleChoiceQuestionRepository.save(answer);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("", "", questionRepository.save(question)));
	}
}
