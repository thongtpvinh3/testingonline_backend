package backend.testingonline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	List<Candidate> findByName(String name);

	List<Candidate> findByEmail(String email);

	List<Candidate> findByPhone(String phone);
}
