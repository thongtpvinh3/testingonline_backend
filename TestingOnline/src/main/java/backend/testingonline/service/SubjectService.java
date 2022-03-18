package backend.testingonline.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Subject;
import backend.testingonline.responeexception.ResponeObject;

public interface SubjectService {

	List<Subject> getAll();

	ResponseEntity<ResponeObject> save(Subject subject);

	ResponseEntity<ResponeObject> deleteById(Integer id);

}
