package backend.testingonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.testingonline.model.MultipleChoiceQuestion;

public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, Integer> {

}
