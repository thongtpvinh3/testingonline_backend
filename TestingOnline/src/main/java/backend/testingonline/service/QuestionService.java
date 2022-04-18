package backend.testingonline.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.EssayQuestion;
import backend.testingonline.model.MultipleChoiceQuestion;
import backend.testingonline.model.Question;
import backend.testingonline.responseException.ResponseObject;

public interface QuestionService {

	List<Question> findAll();

	ResponseEntity<ResponseObject> save(Question newQuestion);

	ResponseEntity<ResponseObject> deleteById(Integer id);

	Question findById(Integer id);

	Question editQuestion(Integer id, Question newQuestion);
	
	Integer getType(Integer idQuestion);
	
	List<Question> findByType(Integer type);

	List<Question> findBySubject(Integer subject);

	List<Question> findByLevel(Integer level);

	Set<Question> getByTestId(Integer idTest);
	
	ResponseEntity<ResponseObject> addAnswerToQuestion(Integer idAnswer, Integer idQuestion);

	ResponseEntity<ResponseObject> addMultipleAnswer(Integer idQuestion, MultipleChoiceQuestion ans);

	ResponseEntity<ResponseObject> addEssayAnswer(Integer idQuestion, EssayQuestion ans);

	ResponseEntity<ResponseObject> deleteMultipleAnswerFromQuestion(Integer idAnswer);

	ResponseEntity<ResponseObject> updateEssayAnswer(Integer idQuestion, EssayQuestion answer);

	ResponseEntity<ResponseObject> updateMCAnswer(Integer idQuestion, MultipleChoiceQuestion answer);

	void removeQuestionFromTest(Integer idQuestion, Integer idTest);
	
	
}
