package backend.testingonline.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import backend.testingonline.model.TempResultOfCandidate;

public interface TempResultService {

	TempResultOfCandidate save(TempResultOfCandidate result);

	void saveAll(List<TempResultOfCandidate> tempResultOfCandidate);
	
	void deleteResult(Integer idCandidate);

}
