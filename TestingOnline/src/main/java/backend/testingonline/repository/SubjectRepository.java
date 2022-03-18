package backend.testingonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
