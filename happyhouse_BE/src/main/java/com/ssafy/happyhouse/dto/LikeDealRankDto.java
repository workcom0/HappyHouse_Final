package com.ssafy.happyhouse.dto;

public class LikeDealRankDto {
	String aptNo;
	String aptName;
	
	public String getAptNo() {
		return aptNo;
	}
	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	@Override
	public String toString() {
		return "LikeDealRankDto [aptNo=" + aptNo + ", aptName=" + aptName + "]";
	}
}
