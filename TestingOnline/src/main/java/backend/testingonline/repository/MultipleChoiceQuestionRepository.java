package backend.testingonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import backend.testingonline.model.MultipleChoiceQuestion;
import backend.testingonline.responeexception.ResponeObject;

public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, Integer> {

}
