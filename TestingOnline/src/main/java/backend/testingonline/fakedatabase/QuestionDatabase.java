package backend.testingonline.fakedatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.testingonline.model.Question;
import backend.testingonline.repository.EssayQuestionRepository;
import backend.testingonline.repository.QuestionRepository;

@Configuration
public class QuestionDatabase {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionDatabase.class);

	@Bean
	CommandLineRunner initQuestionDatabase(QuestionRepository questionRepository,EssayQuestionRepository essayQuestionRepository) {
		return new CommandLineRunner() {

			@Override 
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				Question q1 = new Question(1, 1, "How many people", 1);
				Question q2 = new Question(2, 1, "Code a login form ?", 1);
				Question q3 = new Question(3, 3, "Spring boot app ?", 1);
				Question q4 = new Question(4, 1, "Where ?", 2);
				Question q5 = new Question(5, 2, "code with framework?", 2);
				Question q6 = new Question(6, 3, "React ?", 2);
				Question q7 = new Question(7, 1, "asdsad", 1);
				Question q8 = new Question(8, 1, "asdsadasd", 1);
				Question q9 = new Question(9, 1, "asdsadasdasd ?", 1);
				
				q1.setType(0);
				q2.setType(1);
				q3.setType(0);
				q4.setType(0);
				q5.setType(1);
				q6.setType(1);
				q9.setType(0);
				q7.setType(0);
				q8.setType(0);
				
				
				logger.info("insert data: " + questionRepository.save(q1));
				logger.info("insert data: " + questionRepository.save(q2));
				logger.info("insert data: " + questionRepository.save(q3));
				logger.info("insert data: " + questionRepository.save(q4));
				logger.info("insert data: " + questionRepository.save(q5));
				logger.info("insert data: " + questionRepository.save(q6));
				logger.info("insert data: " + questionRepository.save(q7));
				logger.info("insert data: " + questionRepository.save(q8));
				logger.info("insert data: " + questionRepository.save(q9));
			}
		};
	}
}
