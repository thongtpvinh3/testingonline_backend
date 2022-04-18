package backend.testingonline.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Subject;
import backend.testingonline.responseException.ResponseObject;

public interface SubjectService {

	List<Subject> getAll();

	ResponseEntity<ResponseObject> save(Subject subject);

	ResponseEntity<ResponseObject> deleteById(Integer id);

}
