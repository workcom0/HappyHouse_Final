package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.service.BoardService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@GetMapping
	public ResponseEntity<List<BoardDto>> listBoard() throws Exception {
		return new ResponseEntity<>(boardService.listBoard(), HttpStatus.OK);
	}
	
	@GetMapping("/{boardId}")
	public ResponseEntity<BoardDto> getBoard(@PathVariable String boardId) throws NumberFormatException, SQLException {
		return new ResponseEntity<>(boardService.getBoard(Integer.parseInt(boardId)), HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<String> registerBoard(@RequestBody BoardDto boardDto) throws SQLException {
		if(boardService.registerBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{boardId}")
	public ResponseEntity<String> delteBoard(@PathVariable int boardId) throws NumberFormatException, SQLException {
		if(boardService.deleteBoard(boardId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{boardId}")
	public ResponseEntity<String> updateBoard(@PathVariable int boardId, @RequestBody BoardDto boardDto) throws SQLException {
		if(boardService.updateBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
