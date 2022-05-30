package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.SidoGugunDongDto;

@Mapper
public interface HouseMapper {
	List<SidoGugunDongDto> getSido() throws SQLException;
	List<SidoGugunDongDto> getGugunInSido(String sido) throws SQLException;
	List<SidoGugunDongDto> getDongInGugun(String gugun);
	List<HouseDealDto> getAptInDong(String dong) throws SQLException;
	List<HouseDealDto> getAptInName(String name, String dong) throws SQLException;
}