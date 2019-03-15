package com.pp.redis.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pp.redis.service.RedisService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "RedisController", tags = "redis排序控制类")
public class RedisController {

	@Autowired
	private RedisService redisService;

	@GetMapping("/add")
	@ApiOperation("添加商品")
	public Long addGoods() {
		return redisService.addGoods();
	}

	@GetMapping("/show")
	@ApiOperation("显示商品")
	public Set<String> showGoods() {
		return redisService.showGoods();
	}

	@PostMapping("/buy")
	@ApiOperation("购买商品")
	public Double buyGoods(String name, int num) {
		return redisService.buyGoods(name, num);
	}

	@GetMapping("/getRank")
	@ApiOperation("根据名称，获取排名信息")
	public Map<String, Object> getRank(String name) {
		return redisService.getRank(name);
	}

	@GetMapping("/getRanks")
	@ApiOperation("获取排行榜前五名")
	public Set<String> getRanks() {
		return redisService.getRanks();
	}

	@GetMapping("/getRanking")
	@ApiOperation("获取排行榜前五名和对应的销量")
	public Map<String, Object> getRanking() {
		return redisService.getRanking();
	}
}
