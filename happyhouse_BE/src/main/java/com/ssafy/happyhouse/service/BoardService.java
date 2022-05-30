package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.BoardDto;

public interface BoardService {
	boolean registerBoard(BoardDto boardDto) throws SQLException;
	List<BoardDto> listBoard() throws SQLException;
	BoardDto getBoard(int boardId) throws SQLException;
	boolean updateBoard(BoardDto boardDto) throws SQLException;
	boolean deleteBoard(int boardId) throws SQLException;
}
