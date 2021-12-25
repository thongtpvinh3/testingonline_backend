package backend.testingonline.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.testingonline.model.Test;
import backend.testingonline.repository.TestRepository;
import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	@Override
	public List<Test> getAllTest() {
		return testRepository.findAll();
	}

	@Override
	public Test getWithCode(String code) {
		return testRepository.findByCodeTest(code);
	}

//	@Override
//	public ResponseEntity<ResponeObject> getWithCode(String code) {
//		Optional<Test> foundTest = testRepository.findByCodeTest(code);
//		return (foundTest.isPresent())
//				? ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Found Succes!", foundTest))
//				: ResponseEntity.status(HttpStatus.NOT_FOUND)
//						.body(new ResponeObject("FAILED", "Not Found Test Code: " + code, ""));
//	}
}
