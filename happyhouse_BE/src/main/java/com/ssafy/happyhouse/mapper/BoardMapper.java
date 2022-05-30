package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.BoardDto;

@Mapper
public interface BoardMapper {
	int registerBoard(BoardDto boardDto) throws SQLException;
	List<BoardDto> listBoard(Map<String, Object> map) throws SQLException;
	BoardDto getBoard(int boardId) throws SQLException;
	int updateBoard(BoardDto boardDto) throws SQLException;
	int deleteBoard(int boardId) throws SQLException;
}
