package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.LikeDealDto;
import com.ssafy.happyhouse.dto.LikeDealRankDto;

@Mapper
public interface LikeDealMapper {
	public List<HouseDealDto> selectLikeDeal(String memberId);
	public int insertLikeDeal(LikeDealDto likeDealDto);
	public int deleteLikeDeal(LikeDealDto likeDealDto);
	public List<LikeDealRankDto> likeDealRanking();
}
