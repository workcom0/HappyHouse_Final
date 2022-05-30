package com.ssafy.happyhouse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.LikeDealDto;
import com.ssafy.happyhouse.dto.LikeDealRankDto;
import com.ssafy.happyhouse.mapper.LikeDealMapper;

@Service
public class LikeDealServiceImpl implements LikeDealService{

	@Autowired
	private LikeDealMapper likeDealMapper;
	
	@Override
	public List<HouseDealDto> selectLikeDeal(String memberId) {
		return likeDealMapper.selectLikeDeal(memberId);
	}

	@Override
	public boolean insertLikeDeal(LikeDealDto likeDealDto) {
		return likeDealMapper.insertLikeDeal(likeDealDto)==1;
	}

	@Override
	public boolean deleteLikeDeal(LikeDealDto likeDealDto) {
		return likeDealMapper.deleteLikeDeal(likeDealDto)==1;
	}
	
	@Override
	public List<LikeDealRankDto> getLikeDealRanking() {
		return likeDealMapper.likeDealRanking();
	}

}
