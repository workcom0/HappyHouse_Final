package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping
	public ResponseEntity<List<NoticeDto>> retrieveBoard() throws Exception {
		return new ResponseEntity<List<NoticeDto>>(noticeService.selectNotice(), HttpStatus.OK);
	}
	
	@GetMapping("{noticeNo}")
	public ResponseEntity<NoticeDto> detailBoard(@PathVariable int noticeNo) {
		return new ResponseEntity<NoticeDto>(noticeService.selectNoticeByNo(noticeNo), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody NoticeDto notice) {
		if (noticeService.insertNotice(notice)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("{noticeNo}")
	public ResponseEntity<String> updateBoard(@PathVariable int noticeNo, @RequestBody NoticeDto notice) {
		if (noticeService.updateNotice(notice)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{noticeNo}")
	public ResponseEntity<String> deleteBoard(@PathVariable int noticeNo) {
		if (noticeService.deleteNotice(noticeNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<List<NoticeDto>> searchBoard(@RequestBody HashMap<String, String> map) throws Exception {
		return new ResponseEntity<List<NoticeDto>>(noticeService.searchNotice(map), HttpStatus.OK);
	}
}
