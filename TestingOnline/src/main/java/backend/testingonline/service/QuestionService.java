package backend.testingonline.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.EssayQuestion;
import backend.testingonline.model.MultipleChoiceQuestion;
import backend.testingonline.model.Question;
import backend.testingonline.responeexception.ResponeObject;

public interface QuestionService {

	List<Question> findAll();

	ResponseEntity<ResponeObject> save(Question newQuestion);

	ResponseEntity<ResponeObject> deleteById(Integer id);

	Question findById(Integer id);

	Question editQuestion(Integer id, Question newQuestion);
	
	Integer getType(Integer idQuestion);
	
	List<Question> findByType(Integer type);

	List<Question> findBySubject(Integer subject);

	List<Question> findByLevel(Integer level);

	Set<Question> getByTestId(Integer idTest);
	
	ResponseEntity<ResponeObject> addAnswerToQuestion(Integer idAnswer, Integer idQuestion);

	ResponseEntity<ResponeObject> addMultipleAnswer(Integer idQuestion, MultipleChoiceQuestion ans);

	ResponseEntity<ResponeObject> addEssayAnswer(Integer idQuestion, EssayQuestion ans);

	ResponseEntity<ResponeObject> deleteMultipleAnswerFromQuestion(Integer idAnswer);

	ResponseEntity<ResponeObject> updateEssayAnswer(Integer idQuestion, EssayQuestion answer);

	ResponseEntity<ResponeObject> updateMCAnswer(Integer idQuestion, MultipleChoiceQuestion answer);
	
	
}
