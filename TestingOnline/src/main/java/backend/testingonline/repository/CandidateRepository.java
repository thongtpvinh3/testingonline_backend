package backend.testingonline.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.Test;
import backend.testingonline.responeexception.ResponeObject;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	List<Candidate> findByName(String name);
	
	@Query("SELECT candidate FROM Candidate candidate WHERE candidate.email = :email")
	List<Candidate> findByEmail(@Param("email") String email);
	
	@Query("SELECT candidate FROM Candidate candidate WHERE candidate.phone = :phone")
	List<Candidate> findByPhone(@Param("phone") String phone); 
	
	@org.springframework.data.jpa.repository.Query(value = "SELECT * from Candidate candidate WHERE candidate.id = :id", nativeQuery = true)
	List<Test> aaaCanidateTest(@Param("id") Integer id);
	
	@Transactional
	@Modifying
	@org.springframework.data.jpa.repository.Query("UPDATE Candidate c SET c.isDone = 1 WHERE c.id = :id")
	void setIsDone(@Param("id") int id);

	@Transactional
	@Modifying
	@org.springframework.data.jpa.repository.Query("UPDATE Candidate c SET c.isDone = 1, c.englishMark = 0, c.codingMark=0, c.knowledgeMark=0 WHERE c.id = :idCandidate")
	ResponseEntity<ResponeObject> fixIsDone(@Param("idCandidate") Integer idCandidate);
}
