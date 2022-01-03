package backend.testingonline.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.Question;
import backend.testingonline.model.Test;
import backend.testingonline.responeexception.ResponeObject;

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
