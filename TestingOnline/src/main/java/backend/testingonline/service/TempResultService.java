package backend.testingonline.service;

import java.util.List;

import backend.testingonline.model.TempResultOfCandidate;

public interface TempResultService {

	TempResultOfCandidate save(TempResultOfCandidate result);

	void saveAll(List<TempResultOfCandidate> tempResultOfCandidate);

}
