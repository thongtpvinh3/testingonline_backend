package backend.testingonline.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Levels;
import backend.testingonline.responseException.ResponseObject;

public interface LevelService {

	List<Levels> getAll();

	ResponseEntity<ResponseObject> save(Levels level);

	ResponseEntity<ResponseObject> deleteById(Integer id);

}
