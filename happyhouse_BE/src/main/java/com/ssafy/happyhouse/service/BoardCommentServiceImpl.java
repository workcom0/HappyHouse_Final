package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.BoardCommentDto;
import com.ssafy.happyhouse.mapper.BoardCommentMapper;

@Service
public class BoardCommentServiceImpl implements BoardCommentService{
	@Autowired
	private BoardCommentMapper boardCommentMapper;
	
	@Override
	public boolean registerBoardComment(BoardCommentDto boardDto) throws SQLException {
		return boardCommentMapper.registerBoardComment(boardDto) == 1;
	}

	@Override
	public List<BoardCommentDto> listBoardComment(int boardId) throws SQLException {
		return boardCommentMapper.listBoardComment(boardId);
	}

	@Override
	public boolean updateBoardComment(BoardCommentDto boardDto) throws SQLException {
		return boardCommentMapper.updateBoardComment(boardDto) == 1;
	}

	@Override
	public boolean deleteBoardComment(int commentId) throws SQLException {
		return boardCommentMapper.deleteBoardComment(commentId) == 1;
	}
}
