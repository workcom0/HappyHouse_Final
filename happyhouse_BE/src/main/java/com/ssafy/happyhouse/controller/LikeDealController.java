package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.LikeDealDto;
import com.ssafy.happyhouse.dto.LikeDealRankDto;
import com.ssafy.happyhouse.service.LikeDealService;

@RestController
@RequestMapping("/likedeal")
public class LikeDealController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private LikeDealService likeDealService;
	
	@GetMapping("/{memberId}")
	public ResponseEntity<List<HouseDealDto>> selectLikeDeal(@PathVariable String memberId) throws Exception {
		return new ResponseEntity<List<HouseDealDto>>(likeDealService.selectLikeDeal(memberId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> insertLikeDeal(@RequestBody LikeDealDto likeDealDto) {
		boolean result = false;
		try {
			result = likeDealService.insertLikeDeal(likeDealDto);
		} catch (Exception e) {
			return new ResponseEntity<String>("DUPLICATE", HttpStatus.OK);
		}
		if (result) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteLikeDeal(@RequestBody LikeDealDto likeDealDto) {
		if (likeDealService.deleteLikeDeal(likeDealDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> findLikeDealRanking() {
		Map<String, Object> resultMap = new HashMap<>();
		List<LikeDealRankDto> ranking = likeDealService.getLikeDealRanking();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (ranking.size() > 0) {
			resultMap.put("ranking", ranking);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} else {
			resultMap.put("message", FAIL);
			resultMap.put("ranking", ranking);
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}
