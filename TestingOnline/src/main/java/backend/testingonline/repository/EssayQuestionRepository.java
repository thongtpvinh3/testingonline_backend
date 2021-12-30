package backend.testingonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.testingonline.model.EssayQuestion;

public interface EssayQuestionRepository extends JpaRepository<EssayQuestion, Integer> {

}
