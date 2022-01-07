package backend.testingonline.fakedatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.testingonline.model.EssayQuestion;
import backend.testingonline.repository.EssayQuestionRepository;

@Configuration
public class EssayQuestionDatabase {

	private static final Logger logger = LoggerFactory.getLogger(EssayQuestionDatabase.class);

	@Bean
	CommandLineRunner initEssayDatabase(EssayQuestionRepository essayQuestionRepository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
				EssayQuestion eq1 = new EssayQuestion();
				eq1.setAnswer("I'm think ...");
				EssayQuestion eq2 = new EssayQuestion();
				eq2.setAnswer("I'm said that ...");
	
				logger.info("insert data: " + essayQuestionRepository.save(eq1));
				logger.info("insert data: " + essayQuestionRepository.save(eq2));
			}
		};
	}
}
