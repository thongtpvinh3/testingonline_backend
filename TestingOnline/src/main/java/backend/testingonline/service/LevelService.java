package backend.testingonline.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import backend.testingonline.model.Levels;
import backend.testingonline.responeexception.ResponeObject;

public interface LevelService {

	List<Levels> getAll();

	ResponseEntity<ResponeObject> save(Levels level);

	ResponseEntity<ResponeObject> deleteById(Integer id);

}
