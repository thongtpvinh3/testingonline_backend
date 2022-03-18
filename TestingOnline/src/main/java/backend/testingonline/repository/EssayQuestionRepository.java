package backend.testingonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.EssayQuestion;

@Repository
public interface EssayQuestionRepository extends JpaRepository<EssayQuestion, Integer> {

}
