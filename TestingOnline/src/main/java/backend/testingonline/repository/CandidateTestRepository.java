package backend.testingonline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.CandidateTest;

@Repository
public interface CandidateTestRepository extends JpaRepository<CandidateTest, Integer> {

	@Query("SELECT candidatetest FROM CandidateTest candidatetest WHERE candidatetest.candidateId = :idCandidate AND candidatetest.testId = :idTest")
	CandidateTest findByCandidateIdAndTestId(@Param("idCandidate") Integer idCandidate,@Param("idTest") Integer idTest);
	
	@Query("SELECT ct FROM CandidateTest ct WHERE ct.candidateId = :idCandidate")
	List<CandidateTest> findByCandidateId(@Param("idCandidate") Integer id);

}
