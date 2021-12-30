package backend.testingonline.fakedatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.testingonline.model.Candidate;
import backend.testingonline.repository.CandidateRepository;

@Configuration
public class CandidateDatabase {
	// logger ~= sysout
	private static final Logger logger = LoggerFactory.getLogger(CandidateDatabase.class);

	@Bean
	CommandLineRunner initCandidateDatabase(CandidateRepository candidateRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				Candidate c1 = new Candidate("Quan Vu", 3);
				Candidate c2 = new Candidate("Truong Phi", 2);
				Candidate c3 = new Candidate("Dien Vi", 1);
				Candidate c4 = new Candidate("Hua Chu", 2);
				Candidate c5 = new Candidate("Thai Su Tu", 2);
				Candidate c6 = new Candidate("Chu Thai", 2);

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
