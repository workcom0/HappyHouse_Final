package com.ssafy.happyhouse.dto;

public class BoardDto {
	private Integer boardId;
	private String memberId;
	private String boardTitle;
	private String boardContent;
	private String regTime;
	
	public Integer getBoardId() {
		return boardId;
	}
	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	@Override
	public String toString() {
		return "QnaDto [boardId=" + boardId + ", memberId=" + memberId + ", boardTitle=" + boardTitle + ", boardContent="
				+ boardContent + ", regTime=" + regTime + "]";
	}
}
