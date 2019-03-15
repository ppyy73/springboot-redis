package com.pp.redis.service;

import java.util.Map;
import java.util.Set;

public interface RedisService {

	Long addGoods();

	Set<String> showGoods();

	Double buyGoods(String name, int num);

	Map<String, Object> getRank(String name);

	Set<String> getRanks();

	Map<String, Object> getRanking();
}
