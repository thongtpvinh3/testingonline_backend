package backend.testingonline.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.testingonline.model.Staff;
import backend.testingonline.repository.StaffRepository;

@Configuration
public class Database {

	// logger == sysout
	private static final Logger logger = LoggerFactory.getLogger(Database.class);

	@Bean
	CommandLineRunner initDatabase(StaffRepository staffRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
//				// TODO Auto-generated method stub
//				Staff s = new Staff(2, "thong", "thongtpvinh3", "123");
//				Staff s2 = new Staff(3, "Hai", "haitpvinh3", "123");
//				
//				logger.info("insert data: " + staffRepository.save(s));
//				logger.info("insert data: " + staffRepository.save(s2));
			}
		};
	}
}
