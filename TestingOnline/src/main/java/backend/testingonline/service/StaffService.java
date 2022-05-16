package backend.testingonline.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import backend.testingonline.model.*;
import org.springframework.http.ResponseEntity;

import backend.testingonline.responseException.ResponseObject;

public interface StaffService {

	ResponseEntity<ResponseObject> createStaff(Staff newStaff);
	
	Optional<Candidate> findById(int id);
	
	ResponseEntity<ResponseObject> createTest(Test test);
	
	ResponseEntity<ResponseObject> creaeteType(QuestionType questionType);

	Set<Object> search(ForSearch conditional);
}
