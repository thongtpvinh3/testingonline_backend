package backend.testingonline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.testingonline.model.Question;
import backend.testingonline.repository.QuestionRepository;
import backend.testingonline.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Override
	public List<Question> findAll() {
		return questionRepository.findAll();
	}
	
	
}
