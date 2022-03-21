package backend.testingonline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.TempResultOfCandidate;

@Repository
public interface TempResultRepository extends JpaRepository<TempResultOfCandidate, Integer> {
	
	@Query(value = "SELECT * FROM result_candidate r WHERE r.id_candidate =:id AND r.type =:type", nativeQuery = true)
	List<TempResultOfCandidate> getAnswerOfCandidate(@Param("id") Integer idCandidate,@Param("type") Integer type);
	
	@Query("SELECT t FROM TempResultOfCandidate t WHERE t.idCandidate = ?1 AND t.type = ?2 AND t.idTest = ?3")
	List<TempResultOfCandidate> getAnswerOfCandidateInTest(Integer idCandidate, int i, Integer idTest);

}
