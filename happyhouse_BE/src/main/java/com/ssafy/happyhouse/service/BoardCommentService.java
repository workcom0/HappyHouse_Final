package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.BoardCommentDto;

public interface BoardCommentService {
	boolean registerBoardComment(BoardCommentDto boardCommentDto) throws SQLException;
	List<BoardCommentDto> listBoardComment(int boardId) throws SQLException;
	boolean updateBoardComment(BoardCommentDto boardCommentDto) throws SQLException;
	boolean deleteBoardComment(int commentId) throws SQLException;
}
