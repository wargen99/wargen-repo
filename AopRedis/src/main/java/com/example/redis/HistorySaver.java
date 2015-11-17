package com.example.redis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

@Repository("actionHistory")
public class HistorySaver implements ActionHistoryRepo<String, String> {
	private final String redisKey = "MethodRank";
	
	@Autowired
	private RedisTemplate<String, String> stringRedisTemplate;

	public void addAction(String value) {
		int score = 1;
		this.stringRedisTemplate.opsForZSet().incrementScore("MethodRank",
				value, score);
	}

	public List<RankDto> getRank() {
		Set<String> rankset = this.stringRedisTemplate.opsForZSet()
				.reverseRange("MethodRank", 0L, -1L);
		List<RankDto> rankList = new ArrayList();

		Iterator<String> iters = rankset.iterator();
		int ranky = 1;
		while (iters.hasNext()) {
			RankDto dto = new RankDto();
			dto.setMethodName((String) iters.next());

			Double rank = this.stringRedisTemplate.opsForZSet().score(
					"MethodRank", dto.getMethodName());
			dto.setCallCount(rank.intValue());
			dto.setRank(ranky);
			rankList.add(dto);
			ranky++;
		}
		return rankList;
	}

	public void deleteAction(String key) {
		this.stringRedisTemplate.delete(key);
	}
}
