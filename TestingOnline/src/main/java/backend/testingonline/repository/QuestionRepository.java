package backend.testingonline.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	//Read
	@Query("SELECT question FROM Question question WHERE question.subject = :subject")
	List<Question> findBySubject(@Param("level") Integer subject);
	
	@Query("SELECT question FROM Question question WHERE question.level = :level")
	List<Question> findByLevel(@Param("level") Integer level);
	
	//Dung native Query
	@org.springframework.data.jpa.repository.Query(value = "SELECT q.type FROM question q WHERE id=:id",nativeQuery = true)
	int getTypeById(@Param("id") Integer idQuestion); 

}
