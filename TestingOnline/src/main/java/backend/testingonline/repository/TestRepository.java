package backend.testingonline.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
	
	//Read
	@Query("SELECT test FROM Test test WHERE test.subject = :subject")
	public List<Test> findBySubject(@Param("subject") Integer subject);
	
	@Query("SELECT test FROM Test test WHERE test.is_done = :isdone")
	List<Test> findByIsDone(@Param("isdone") Integer isdone);
	
	@Query("SELECT test FROM Test test WHERE test.level = :level")
	List<Test> findByLevel(@Param("level") Integer level);
	
	@Query("SELECT test FROM Test test WHERE test.code_test LIKE \":code\"")
	Test findByCodeTest(@Param("code") String code);
	
	@Query("SELECT test FROM Test test WHERE test.name like %:name%")
	List<Test> findByName(@Param("name") String name);
	
	@Transactional
	@org.springframework.data.jpa.repository.Modifying
	@org.springframework.data.jpa.repository.Query(value = "UPDATE Test test SET is_done = 1 WHERE id = ?1", nativeQuery = true)
	void setTestIsDone(Integer id);
	
//	@Query("SELECT test FROM Test test WHERE test.candidate = :candidate")
//	List<Test> findByCandidates(@Param("candidate") Candidate candidate);
	
//	@Query("")
//	Object addQuestion(@Param("question") Question newQuestion);

//	ResponseEntity<ResponeObject> addQuestion();
	
//	@Query("")
//	ResponseEntity<ResponeObject> save(@Param("id") Integer id ,@Param("question") Question newQuestion);
	
//	List<Test> findWithCandidate(Candidate candidate);
	
	//Create
	//Delete
	//Update
}
