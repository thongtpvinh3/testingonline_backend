package backend.testingonline.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import backend.testingonline.model.TempResultOfCandidate;

@Service
public class RedisCandidateDoTestCache {

	private ValueOperations<String, Object> valueOps;

	private HashOperations<String, String, TempResultOfCandidate> hashOps;

	public RedisCandidateDoTestCache(final RedisTemplate<String, Object> redisTemplate) {
		valueOps = redisTemplate.opsForValue();
		hashOps = redisTemplate.opsForHash();
	}

	// ---------------------Dung Hash Map ------------------
	
	public void saveEssay(TempResultOfCandidate temp, Integer idCandidate, Integer idQuestion) {
		try {
			String keyGen = idQuestion.toString()+":"+idCandidate;
			hashOps.put("ans", keyGen, temp);
		} catch (Exception e) {
			System.out.println("Ko tim thay cau tra loi");
		}
	}
	
	public void saveMultiple(TempResultOfCandidate temp, Integer idCandidate, Integer idQuestion) {
		try {
			String keyGen = idQuestion.toString()+":"+idCandidate.toString();
			hashOps.put("ans", keyGen, temp);
			System.out.println(idQuestion);
		} catch (Exception e) {
			System.out.println("khong tim thay cau tra loi");
		}
	}

	public Object getHashCacheAns(String key) {
		return hashOps.entries(key);
	}
	
	public Map<Integer, TempResultOfCandidate> getCandidateCacheAns(Integer idCandidate, String key) {
		Map<String, TempResultOfCandidate> cacheAns = hashOps.entries(key);
		Map<Integer, TempResultOfCandidate> thisCandidateCache = new LinkedHashMap<>();
		for (Map.Entry<String, TempResultOfCandidate> e: cacheAns.entrySet()) {
			String thisKey = e.getKey();
			String[] str = thisKey.split(":");
			if (Integer.parseInt(str[1]) == idCandidate) {
				thisCandidateCache.put(Integer.parseInt(str[0]), e.getValue());
			}
		}
		return thisCandidateCache;
	}

	public void delete(String key, Integer idCandidate) {
		Map<String, TempResultOfCandidate> cacheAns = hashOps.entries(key);
		
//		for (Iterator<Entry<String, TempResultOfCandidate>> it = cacheAns.entrySet().iterator(); it.hasNext();) {
//			Entry<String, TempResultOfCandidate> m = it.next();
//			String thisKey = m.getKey();
//			String[] str = thisKey.split(":");
//			if (str[1].equals(idCandidate.toString())) {
//				cacheAns.remove(thisKey);
//			}
//		}
		Map<String, TempResultOfCandidate> toRemove = new LinkedHashMap<>();
		for (Map.Entry<String, TempResultOfCandidate> e: cacheAns.entrySet()) {
			String thisKey = e.getKey();
			String[] str = thisKey.split(":");
			if (str[1].equals(idCandidate.toString())) {
				toRemove.put(thisKey, e.getValue());
			}
		}
		cacheAns.entrySet().removeAll(toRemove.entrySet());
		hashOps.getOperations().delete(key);
		hashOps.putAll("ans", cacheAns);
	}
	
	public void deleteAll() {
		hashOps.getOperations().delete("ans");
	}

	// --------------- Dung List ------------------------
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
