package backend.testingonline.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Question;
import backend.testingonline.responeexception.ResponeObject;

public interface QuestionService {

	List<Question> findAll();

	ResponseEntity<ResponeObject> save(Question newQuestion);

	ResponseEntity<ResponeObject> deleteById(Integer id);

	Question findById(Integer id);

	Question editQuestion(Integer id, Question newQuestion);
	
	
}
