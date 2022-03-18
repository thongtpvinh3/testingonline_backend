package backend.testingonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.MultipleChoiceQuestion;

@Repository
public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, Integer> {
	
	@org.springframework.data.jpa.repository.Query(value = "SELECT * FROM mc_question m WHERE m.id = :idAnswer AND m.istrue = :isTrue", nativeQuery = true)
	MultipleChoiceQuestion findWithIdAndisTrue(@Param("idAnswer") Integer idAnswer, @Param("isTrue") Integer isTrue);

}
