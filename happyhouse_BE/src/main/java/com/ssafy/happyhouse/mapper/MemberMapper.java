package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.dto.MemberDto;

@Mapper
public interface MemberMapper {
	void register(MemberDto memberDto) throws SQLException;
	int duplicateMemberCheck(String memberId) throws SQLException;
	void insertUserAuthorities(MemberDto memberDto) throws SQLException;
	MemberDto login(MemberDto memberDto) throws SQLException;
	MemberDto findById(MemberDto memberDto) throws SQLException;
	MemberDto memberInfo(String memberId) throws SQLException;
	void changeMemberInfo(MemberDto memberDto) throws SQLException;
	void removeMember(String memberId) throws SQLException;
	String checkBeforeChangePw(@Param("memberId") String memberId, @Param("email") String email, @Param("tel") String tel) throws SQLException;
	void changePassword(String memberId, String changePw) throws SQLException;
}
