package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public boolean registerBoard(BoardDto boardDto) throws SQLException {
		return boardMapper.registerBoard(boardDto) == 1;
	}

	@Override
	public List<BoardDto> listBoard() throws SQLException {
		Map<String, Object> param = new HashMap<String, Object>();
		return boardMapper.listBoard(param);
	}

	@Override
	public BoardDto getBoard(int boardId) throws SQLException {
		return boardMapper.getBoard(boardId);
	}

	@Override
	public boolean updateBoard(BoardDto boardDto) throws SQLException {
		return boardMapper.updateBoard(boardDto) == 1;
	}

	@Override
	public boolean deleteBoard(int boardId) throws SQLException {
		return boardMapper.deleteBoard(boardId) == 1;
	}
}
