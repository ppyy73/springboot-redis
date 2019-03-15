package com.pp.redis.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

import com.pp.redis.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private StringRedisTemplate redisTemplate;

	private static final String RANKING = "ranking";

	String[] phone = { "apple", "vivo", "oppe", "华为", "小米", "一加" };

	@Override
	public Long addGoods() {
		Set<TypedTuple<String>> tuples = new HashSet<>();
		for (int i = 0; i < phone.length; i++) {
			DefaultTypedTuple<String> dtt = new DefaultTypedTuple<String>(phone[i], 0.0);
			tuples.add(dtt);
		}
		Long num = redisTemplate.opsForZSet().add(RANKING, tuples);
		return num;
	}

	@Override
	public Set<String> showGoods() {
		Set<String> set = redisTemplate.opsForZSet().range(RANKING, 0, -1);
		return set;
	}

	@Override
	public Double buyGoods(String name, int num) {
		Double add = redisTemplate.opsForZSet().incrementScore(RANKING, name, num);
		return add;
	}

	@Override
	public Map<String, Object> getRank(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long rank = redisTemplate.opsForZSet().reverseRank(RANKING, name);
		Double score = redisTemplate.opsForZSet().score(RANKING, name);
		map.put("排名", rank + 1);
		map.put("销量", score);
		return map;
	}

	@Override
	public Set<String> getRanks() {
		Set<String> set = redisTemplate.opsForZSet().reverseRange(RANKING, 0, 4);
		return set;
	}

	@Override
	public Map<String, Object> getRanking() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Set<String> set = redisTemplate.opsForZSet().reverseRange(RANKING, 0, 4);
		for (String t : set) {
			Double score = redisTemplate.opsForZSet().score(RANKING, t);
			map.put(t, score);
		}
		return map;
	}

}
