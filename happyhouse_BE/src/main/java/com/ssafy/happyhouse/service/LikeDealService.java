package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.LikeDealDto;
import com.ssafy.happyhouse.dto.LikeDealRankDto;

public interface LikeDealService {
	public List<HouseDealDto> selectLikeDeal(String memberId);
	public boolean insertLikeDeal(LikeDealDto likeDealDto);
	public boolean deleteLikeDeal(LikeDealDto likeDealDto);
	public List<LikeDealRankDto> getLikeDealRanking();
}
