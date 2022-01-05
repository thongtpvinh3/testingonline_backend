package backend.testingonline.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.Test;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	List<Candidate> findByName(String name);
	
	@Query("SELECT candidate FROM Candidate candidate WHERE candidate.email = :email")
	List<Candidate> findByEmail(@Param("email") String email);
	
	@Query("SELECT candidate FROM Candidate candidate WHERE candidate.phone = :phone")
	List<Candidate> findByPhone(@Param("phone") String phone); 
	
	@org.springframework.data.jpa.repository.Query(value = "SELECT * from Candidate candidate WHERE candidate.id = :id", nativeQuery = true)
	List<Test> aaaCanidateTest(@Param("id") Integer id);
}
