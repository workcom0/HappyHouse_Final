package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.MemberDto;

public interface MemberService {
	public void register(MemberDto memberDto) throws Exception;
	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto memberGetInfo(String memberId) throws Exception;
	public void memberChangeInfo(MemberDto memberDto) throws Exception;
	public void remove(String memberId) throws Exception; 
	public void change(String memberId, String email, String tel, String changePw) throws Exception; 
}
