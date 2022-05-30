package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.SidoGugunDongDto;
import com.ssafy.happyhouse.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	private HouseMapper houseMapper;
	
	@Override
	public List<SidoGugunDongDto> getSido() throws SQLException {
		return houseMapper.getSido();
	}

	@Override
	public List<SidoGugunDongDto> getGugunInSido(String sido) throws SQLException {
		return houseMapper.getGugunInSido(sido);
	}

	@Override
	public List<SidoGugunDongDto> getDongInGugun(String gugun) {
		return houseMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseDealDto> getAptInDong(String dong) throws SQLException {
		return houseMapper.getAptInDong(dong);
	}

	@Override
	public List<HouseDealDto> getAptInName(String name, String dong) throws SQLException {
		return houseMapper.getAptInName(name, dong);
	}
}