package com.ssafy.happyhouse.dto;

public class NoticeDto {
	int noticeNo;
	String memberId;
	String subject;
	String content;
	String regTime;
	
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	
	@Override
	public String toString() {
		return "NoticeDto [noticeNo=" + noticeNo + ", memberId=" + memberId + ", subject=" + subject + ", content="
				+ content + ", regTime=" + regTime + "]";
	}
}
