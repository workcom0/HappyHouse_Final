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

import com.ssafy.happyhouse.dto.BoardCommentDto;
import com.ssafy.happyhouse.service.BoardCommentService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board/comment")
public class BoardCommentController {
	@Autowired
	private BoardCommentService boardCommentService;
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@GetMapping("/{boardId}")
	public ResponseEntity<List<BoardCommentDto>> listBoardComment(@PathVariable String boardId) throws Exception {
		return new ResponseEntity<>(boardCommentService.listBoardComment(Integer.parseInt(boardId)), HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<String> registerBoardComment(@RequestBody BoardCommentDto boardCommentDto) throws SQLException {
		if(boardCommentService.registerBoardComment(boardCommentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<String> delteBoardComment(@PathVariable int commentId) throws NumberFormatException, SQLException {
		if(boardCommentService.deleteBoardComment(commentId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{commentId}")
	public ResponseEntity<String> updateBoardComment(@PathVariable int commentId, @RequestBody BoardCommentDto boardCommentDto) throws SQLException {
		if(boardCommentService.updateBoardComment(boardCommentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
