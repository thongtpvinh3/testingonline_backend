package backend.testingonline.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
	List<Test> getByName(@Param("name") String name);
	
//	List<Test> findWithCandidate(Candidate candidate);
	
	//Create
	//Delete
	//Update
}
