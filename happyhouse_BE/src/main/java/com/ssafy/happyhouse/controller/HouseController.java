package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.SidoGugunDongDto;
import com.ssafy.happyhouse.service.HouseService;

@RestController
@RequestMapping("/house")
public class HouseController {
	
	@Autowired
	private HouseService houseService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunDongDto>> sido() throws Exception {
		List<SidoGugunDongDto> list = houseService.getSido();
		return new ResponseEntity<List<SidoGugunDongDto>>(houseService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunDongDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		List<SidoGugunDongDto> list = houseService.getGugunInSido(sido);
		return new ResponseEntity<List<SidoGugunDongDto>>(houseService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<SidoGugunDongDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		List<SidoGugunDongDto> list = houseService.getDongInGugun(gugun);
		return new ResponseEntity<List<SidoGugunDongDto>>(houseService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/listByDong")
	public ResponseEntity<List<HouseDealDto>> listByDong(@RequestParam("dong") String dong) throws Exception {
		List<HouseDealDto> list = houseService.getAptInDong(dong);
		return new ResponseEntity<List<HouseDealDto>>(houseService.getAptInDong(dong), HttpStatus.OK);
	}
	
	@GetMapping("/listByName")
	public ResponseEntity<List<HouseDealDto>> listByName(@RequestParam("name") String name, @RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseDealDto>>(houseService.getAptInName(name, dong), HttpStatus.OK);
	}
}