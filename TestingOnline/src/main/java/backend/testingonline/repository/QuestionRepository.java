package backend.testingonline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.Levels;
import backend.testingonline.model.Question;
import backend.testingonline.model.Subject;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	//Read
	@Query("SELECT question FROM Question question WHERE question.subject = :subject")
	List<Question> findBySubject(@Param("subject") Subject subject);
	
	@Query("SELECT question FROM Question question WHERE question.level = :level")
	List<Question> findByLevel(@Param("level") Levels level);
	
//	@Query("SELECT q.type FROM question q WHERE id=:id")
//	int getTypeById(@Param("id") Integer idQuestion);
	
//	@Query("SELECT question FROM Question question WHERE question.type = :type")
//	List<Question> findByType(@Param("types")Integer type); 
	
//	@org.springframework.data.jpa.repository.Query(value = "")
//	Integer getSubject(Integer idQuestion);
	
//	Integer getLevel(Integer idQuestion);
}
