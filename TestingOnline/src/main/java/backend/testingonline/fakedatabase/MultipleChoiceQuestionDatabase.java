package backend.testingonline.fakedatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.testingonline.model.MultipleChoiceQuestion;
import backend.testingonline.repository.MultipleChoiceQuestionRepository;

//@Configuration
public class MultipleChoiceQuestionDatabase {

	private static final Logger logger = LoggerFactory.getLogger(MultipleChoiceQuestionDatabase.class);

//	@Bean
	CommandLineRunner initDatabase(MultipleChoiceQuestionRepository multipleChoiceQuestionRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				
				MultipleChoiceQuestion mq1 = new MultipleChoiceQuestion();
				MultipleChoiceQuestion mq2 = new MultipleChoiceQuestion();
				MultipleChoiceQuestion mq3 = new MultipleChoiceQuestion();
				logger.info("insert data: " + multipleChoiceQuestionRepository.save(mq1));
				logger.info("insert data: " + multipleChoiceQuestionRepository.save(mq2));
				logger.info("insert data: " + multipleChoiceQuestionRepository.save(mq3));
			}
		};
	}
}
