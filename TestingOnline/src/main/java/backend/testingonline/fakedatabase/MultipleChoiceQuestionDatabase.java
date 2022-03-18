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

	@Bean
	CommandLineRunner initMultiChoiceDatabase(MultipleChoiceQuestionRepository multipleChoiceQuestionRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				
				MultipleChoiceQuestion mq1A = new MultipleChoiceQuestion();
				MultipleChoiceQuestion mq1B = new MultipleChoiceQuestion();
				MultipleChoiceQuestion mq1C = new MultipleChoiceQuestion();
				MultipleChoiceQuestion mq2A = new MultipleChoiceQuestion();
				MultipleChoiceQuestion mq2B = new MultipleChoiceQuestion();
				MultipleChoiceQuestion mq2C = new MultipleChoiceQuestion();
			
				mq1A.setAnswer("1A");
				mq1A.setIsTrue(1);
				mq1B.setAnswer("1B");
				mq1B.setIsTrue(0);
				mq1C.setAnswer("1C");
				mq1C.setIsTrue(0);
	
				mq2A.setAnswer("2A");
				mq2A.setIsTrue(1);
				mq2B.setAnswer("2B");
				mq2B.setIsTrue(0);
				mq2C.setAnswer("2C");
				mq2C.setIsTrue(0);
				
				logger.info("insert data: " + multipleChoiceQuestionRepository.save(mq1A));
				logger.info("insert data: " + multipleChoiceQuestionRepository.save(mq1B));
				logger.info("insert data: " + multipleChoiceQuestionRepository.save(mq1C));
				logger.info("insert data: " + multipleChoiceQuestionRepository.save(mq2A));
				logger.info("insert data: " + multipleChoiceQuestionRepository.save(mq2B));
				logger.info("insert data: " + multipleChoiceQuestionRepository.save(mq2C));
			}
		};
	}
}
