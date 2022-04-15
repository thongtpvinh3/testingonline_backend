package backend.testingonline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.testingonline.model.TempResultOfCandidate;
import backend.testingonline.repository.TempResultRepository;
import backend.testingonline.service.TempResultService;

@Service
public class TempResultServiceImpl implements TempResultService {

	@Autowired
	private TempResultRepository tempResultRepository;

	@Override
	public TempResultOfCandidate save(TempResultOfCandidate result) {
		return tempResultRepository.save(result);

	}

	@Override
	public void saveAll(List<TempResultOfCandidate> tempResultOfCandidate) {
		tempResultRepository.saveAll(tempResultOfCandidate);
	}

	@Override
	public void deleteResult(Integer idCandidate) {
		tempResultRepository.deleteResult(idCandidate);
	}

}
