package backend.testingonline.fakedatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.testingonline.model.Staff;
import backend.testingonline.repository.StaffRepository;

@Configuration
public class StaffDatabase {

	// logger ~= sysout
	private static final Logger logger = LoggerFactory.getLogger(StaffDatabase.class);

	@Bean
	CommandLineRunner initStaffDatabase(StaffRepository staffRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				 Staff s1 = new Staff(2, "Luu Bi", "luubi", "123");
				 Staff s2 = new Staff(3, "Tao Thao", "taothao", "123");
				 Staff s3 = new Staff(3, "Ton Quyen", "tonquyen", "123");
				
				 logger.info("insert data: " + staffRepository.save(s1));
				 logger.info("insert data: " + staffRepository.save(s2));
				 logger.info("insert data: " + staffRepository.save(s3));
			}
		};
	}
}
