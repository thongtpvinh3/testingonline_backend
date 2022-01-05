package backend.testingonline.fakedatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.testingonline.model.Levels;
import backend.testingonline.repository.LevelRepository;

@Configuration
public class LevelsDatabase {
	
	private static final Logger logger = LoggerFactory.getLogger(LevelsDatabase.class);
	
	@Bean
	CommandLineRunner initLevelsDatabase(LevelRepository levelRepository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				Levels lv1 = new Levels();
				lv1.setName("Fresher Developer");
				Levels lv2 = new Levels();
				lv2.setName("Junior Developer");
				Levels lv3 = new Levels();
				lv3.setName("Senior Developer");
				
				logger.info("insert data: "+ levelRepository.save(lv1));
				logger.info("insert data: "+ levelRepository.save(lv2));
				logger.info("insert data: "+ levelRepository.save(lv3));
			}
		};
	}
	
}
