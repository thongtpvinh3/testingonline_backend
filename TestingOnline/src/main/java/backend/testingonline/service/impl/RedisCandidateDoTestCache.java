package backend.testingonline.service.impl;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import backend.testingonline.model.MultipleChoiceQuestion;
import backend.testingonline.model.TempResultOfCandidate;
import backend.testingonline.repository.EssayQuestionRepository;
import backend.testingonline.repository.MultipleChoiceQuestionRepository;

@Service
public class RedisCandidateDoTestCache {

	private ValueOperations<String, Object> valueOps;
	
	private HashOperations hashOps;
	
	@Autowired
	private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
	
	@Autowired
	private EssayQuestionRepository essayQuestionRepository;

	public RedisCandidateDoTestCache(final RedisTemplate<String, Object> redisTemplate) {
		valueOps = redisTemplate.opsForValue();
		hashOps = redisTemplate.opsForHash();
	}
	
	//---------------------Dung Hash Map ------------------
	public void save(TempResultOfCandidate temp) {
		try {
			int idQuestion = multipleChoiceQuestionRepository.getById(temp.getIdAnswer()).getQuestion().getId();
			hashOps.put("ans", idQuestion, temp);
		} catch (Exception e) {
			System.out.println("Ko co cau tra loi IdAns cho cau hoi ");
		}
	}
	
	public Object getHashCacheAns(String key) {
		return hashOps.entries(key);
	}
	
	public void delete(String key) {
		hashOps.getOperations().delete(key);
	}
	
	//--------------- Dung List ------------------------
	public void cache(final String key, final Object data) {
		valueOps.set(key, data, 2, TimeUnit.HOURS);
	}

	public Object getCachedValue(final String key) {
		return valueOps.get(key);
	}

	public void deleteCachedValue(final String key) {
		valueOps.getOperations().delete(key);
	}
}
