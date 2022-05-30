package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.SidoGugunDongDto;

public interface HouseService {
	List<SidoGugunDongDto> getSido() throws SQLException;
	List<SidoGugunDongDto> getGugunInSido(String sido) throws SQLException;
	List<SidoGugunDongDto> getDongInGugun(String gugun);
	List<HouseDealDto> getAptInDong(String dong) throws SQLException;
	List<HouseDealDto> getAptInName(String name, String dong) throws SQLException;
}