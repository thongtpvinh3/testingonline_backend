package backend.testingonline.fakedatabase;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.testingonline.model.Test;
import backend.testingonline.repository.TestRepository;

//@Configuration
public class TestDatabase {
	private static final Logger logger = LoggerFactory.getLogger(TestDatabase.class);

	@Bean
	CommandLineRunner initDatabase(TestRepository testRepository) {
		return new CommandLineRunner() { 

			@Override
			public void run(String... args) throws Exception {
				
//				Test t1 = new Test(1, 1, "Bai test english lv1", 0, "ENG1");
//				Test t2 = new Test(2, 1, "Bai test coding lv1", 0, "CODE1");
//				Test t3 = new Test(3, 1, "Bai test kien thuc chung lv1", 0, "KNOW1");
//				Test t4 = new Test(1, 2, "Bai test english lv2", 0, "ENG2");
//				Test t5 = new Test(2, 2, "Bai test coding lv2", 0, "CODE2");
//				Test t6 = new Test(3, 2, "Bai test kien thuc chung lv2", 0, "KNOW2");
//				
//				t1.setDateTest(LocalDateTime.of(2022, 1, 20, 23, 00));
//				t1.setTime(LocalTime.of(0,55,0));
//				
//				logger.info("insert data: " + testRepository.save(t1));
//				logger.info("insert data: " + testRepository.save(t2));
//				logger.info("insert data: " + testRepository.save(t3));
//				logger.info("insert data: " + testRepository.save(t4));
//				logger.info("insert data: " + testRepository.save(t5));
//				logger.info("insert data: " + testRepository.save(t6));
			}
		};
	}
}
