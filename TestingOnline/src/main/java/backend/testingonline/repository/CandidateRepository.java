package backend.testingonline.repository;

import backend.testingonline.model.Candidate;
import backend.testingonline.responseException.ResponseObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	@Query(value = "SELECT * FROM candidate candidate WHERE candidate.name LIKE %:name%",nativeQuery = true)
	List<Candidate> findByName(@Param("name") String name);
	
	@org.springframework.data.jpa.repository.Query(value = "SELECT * FROM candidate candidate WHERE candidate.email LIKE %:email%", nativeQuery = true)
	List<Candidate> findByEmail(@Param("email") String email);
	
	@org.springframework.data.jpa.repository.Query(value = "SELECT * FROM candidate candidate WHERE candidate.phone LIKE %:phone%",nativeQuery = true)
	List<Candidate> findByPhone(@Param("phone") String phone); 
	
	@Transactional
	@Modifying
	@org.springframework.data.jpa.repository.Query("UPDATE Candidate c SET c.isDone = 1 WHERE c.id = :id")
	void setIsDone(@Param("id") Integer id);

	@Transactional
	@Modifying
	@org.springframework.data.jpa.repository.Query("UPDATE Candidate c SET c.isDone = 1, c.englishMark = 0, c.codingMark=0, c.knowledgeMark=0 WHERE c.id = :idCandidate")
	ResponseEntity<ResponseObject> fixIsDone(@Param("idCandidate") Integer idCandidate);

	@Query("SELECT c FROM Candidate c WHERE c.isDone = :done")
	List<Candidate> findByIsDone(@Param("done") Integer done);
	
	@org.springframework.data.jpa.repository.Query("SELECT c FROM Candidate c WHERE c.id = :id")
	Candidate getById(@Param("id") Integer idCandidate);

    List<Candidate> findByDates(LocalDate cSearch);

	List<Candidate> findByPosition(String cSearch);

	@Query(value = "SELECT * FROM candidate c WHERE c.id_level = :idLevel",nativeQuery = true)
	List<Candidate> findByLevel(@Param("idLevel") Integer idLevel);
}
