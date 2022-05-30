package com.ssafy.happyhouse.dto;

public class LikeDealDto {
	String aptNo;
	String memberId;
	
	public String getAptNo() {
		return aptNo;
	}
	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Override
	public String toString() {
		return "LikeDealDto [aptNo=" + aptNo + ", memberId=" + memberId + "]";
	}

}
