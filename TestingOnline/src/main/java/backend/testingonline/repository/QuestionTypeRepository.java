package backend.testingonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.testingonline.model.QuestionType;

public interface QuestionTypeRepository extends JpaRepository<QuestionType, Integer> {

}
