package backend.testingonline.fakedatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.testingonline.model.Subject;
import backend.testingonline.repository.SubjectRepository;

@Configuration
public class SubjectDatabase {
	
private static final Logger logger = LoggerFactory.getLogger(SubjectDatabase.class);
	
	@Bean
	CommandLineRunner initSubjectDatabase(SubjectRepository subjectRepository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Subject sub1 = new Subject();
				sub1.setName("Bai test English");
				Subject sub2 = new Subject();
				sub2.setName("Bai test Coding");
				Subject sub3 = new Subject();
				sub3.setName("Bai test Knowledge");
				
				logger.info("insert data: "+ subjectRepository.save(sub1));
				logger.info("insert data: "+ subjectRepository.save(sub2));
				logger.info("insert data: "+ subjectRepository.save(sub3));
			}
		};
	}
}
