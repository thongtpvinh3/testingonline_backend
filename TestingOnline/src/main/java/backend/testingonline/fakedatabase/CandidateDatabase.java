package backend.testingonline.fakedatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.testingonline.model.Candidate;
import backend.testingonline.repository.CandidateRepository;

//@Configuration
public class CandidateDatabase { 
	// logger ~= sysout
	private static final Logger logger = LoggerFactory.getLogger(CandidateDatabase.class);

	@Bean
	CommandLineRunner initCandidateDatabase(CandidateRepository candidateRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				Candidate c1 = new Candidate("Quan Vu", "0123", "quanvu@gmail.com");
				c1.setLevel(3);
				Candidate c2 = new Candidate("Truong Phi", "0124","truongphi@gmail.com");
				c2.setLevel(1);
				Candidate c3 = new Candidate("Dien Vi", "0125","dienvi@gmail.com");
				c3.setLevel(2);
				Candidate c4 = new Candidate("Hua Chu", "0126", "huachu@gmail.com");
				Candidate c5 = new Candidate("Thai Su Tu", "0127", "thaisutu@gmail.com");
				Candidate c6 = new Candidate("Chu Thai", "0128", "chuthai@gmail.com");
				
				
				
				logger.info("insert data: " + candidateRepository.save(c1));
				logger.info("insert data: " + candidateRepository.save(c2));
				logger.info("insert data: " + candidateRepository.save(c3));
				logger.info("insert data: " + candidateRepository.save(c4));
				logger.info("insert data: " + candidateRepository.save(c5));
				logger.info("insert data: " + candidateRepository.save(c6));
			}
		};
	}
}
