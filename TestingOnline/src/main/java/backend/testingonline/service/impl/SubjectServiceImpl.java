package backend.testingonline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.testingonline.model.Subject;
import backend.testingonline.repository.SubjectRepository;
import backend.testingonline.responseException.ResponseObject;
import backend.testingonline.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public List<Subject> getAll() {
		return subjectRepository.findAll();
	}

	@Override
	public ResponseEntity<ResponseObject> save(Subject subject) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK!", "Add success!", subjectRepository.save(subject)));
	}

	@Override
	public ResponseEntity<ResponseObject> deleteById(Integer id) {
		subjectRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Delete Success!", ""));
	}

}
