package backend.testingonline.fakedatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.testingonline.model.EssayQuestion;
import backend.testingonline.repository.EssayQuestionRepository;

//@Configuration
public class EssayQuestionDatabase {

	private static final Logger logger = LoggerFactory.getLogger(EssayQuestionDatabase.class);

//	@Bean
	CommandLineRunner initDatabase(EssayQuestionRepository essayQuestionRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				
				EssayQuestion eq1 = new EssayQuestion();
				EssayQuestion eq2 = new EssayQuestion();
				EssayQuestion eq3 = new EssayQuestion();
				
				logger.info("insert data: " + essayQuestionRepository.save(eq1));
				logger.info("insert data: " + essayQuestionRepository.save(eq2));
				logger.info("insert data: " + essayQuestionRepository.save(eq3));
			}
		};
	}
}
