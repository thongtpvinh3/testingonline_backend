package backend.testingonline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.testingonline.model.Levels;
import backend.testingonline.repository.LevelRepository;
import backend.testingonline.responeexception.ResponeObject;
import backend.testingonline.service.LevelService;

@Service
public class LevelServiceImpl implements LevelService {

	@Autowired
	private LevelRepository levelRepository;

	@Override
	public List<Levels> getAll() {
		return levelRepository.findAll();
	}

	@Override
	public ResponseEntity<ResponeObject> save(Levels level) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK!", "Add success!", levelRepository.save(level)));
	}

	@Override
	public ResponseEntity<ResponeObject> deleteById(Integer id) {
		levelRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Delete Success!", ""));
	}

}
