package com.ssafy.happyhouse.dto;

public class BoardCommentDto {
	private Integer commentId;
	private String memberId;
	private Integer boardId;
	private String commentContent;
	private String regTime;
	
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Integer getBoardId() {
		return boardId;
	}
	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	@Override
	public String toString() {
		return "QnaCommentDto [commentId=" + commentId + ", memberId=" + memberId + ", boardId=" + boardId
				+ ", commentContent=" + commentContent + ", regTime=" + regTime + "]";
	}
}
