package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.BoardCommentDto;

@Mapper
public interface BoardCommentMapper {
	int registerBoardComment(BoardCommentDto boardCommentDto) throws SQLException;
	List<BoardCommentDto> listBoardComment(int boardId) throws SQLException;
	int updateBoardComment(BoardCommentDto boardCommentDto) throws SQLException;
	int deleteBoardComment(int commentId) throws SQLException;
}
