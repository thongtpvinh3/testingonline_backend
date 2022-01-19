package backend.testingonline.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisCandidateDoTestCache {

	private ValueOperations<String, Object> valueOps;

	public RedisCandidateDoTestCache(final RedisTemplate<String, Object> redisTemplate) {
		valueOps = redisTemplate.opsForValue();
	}

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
