package backend.testingonline.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	//Read
	@Modifying
	@Query("SELECT question FROM Question question WHERE question.subject = :subject")
	List<Question> findBySubject(@Param("level") Integer subject);
	
	@Modifying
	@Query("SELECT question FROM Question question WHERE question.level = :level")
	List<Question> findByLevel(@Param("level") Integer level);
	
	@Modifying
	@Query("SELECT question FROM Question question WHERE question.level = :level")
	List<Question> findBySubject(@Param("level") String level);
	
}
