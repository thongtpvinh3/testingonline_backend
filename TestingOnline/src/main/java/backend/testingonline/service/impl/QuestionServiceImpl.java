package backend.testingonline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.testingonline.model.Question;
import backend.testingonline.model.Test;
import backend.testingonline.repository.QuestionRepository;
import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	
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
		questionRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Delete Success!", ""));
	}

	@Override
	public Question findById(Integer id) {
		return questionRepository.findById(id).get();
	}

	@Override
	public Question editQuestion(Integer id, Question newQuestion) {
		Question foundQuestion = questionRepository.getById(id);

		foundQuestion.setId(newQuestion.getId());
		foundQuestion.setContent(newQuestion.getContent());
		foundQuestion.setLevel(newQuestion.getLevel());
		foundQuestion.setSubject(newQuestion.getSubject());
		foundQuestion.setType(newQuestion.getType());

		return questionRepository.save(foundQuestion);
	}
}
