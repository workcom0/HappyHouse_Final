package com.ssafy.happyhouse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.controller.MemberController;
import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	public MemberServiceImpl(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
		this.memberMapper = memberMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public void register(MemberDto memberDto) throws Exception {
		// 중복 회원 검사
		validateDuplicateMember(memberDto);
		
		// 패스워드 암호화
		memberDto.setMemberPw(encodePassword(memberDto.getMemberPw()));
		
		// 사용자 등록
		memberMapper.register(memberDto);
		
		// 사용자 권한 등록
		memberMapper.insertUserAuthorities(memberDto);
		
		logger.debug(memberDto.getMemberId()+" 회원의 사용자 등록 및 권한 등록이 성공했습니다.");
	}
	
	/**
     * 중복 회원 검증
     */
    private void validateDuplicateMember(MemberDto memberDto) throws Exception {
    	// 중복 회원 검사 - 결과 값이 0이면 중복 회원 없음
    	int duplicateCheck = memberMapper.duplicateMemberCheck(memberDto.getMemberId());
    	
        if (duplicateCheck != 0) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    
    /**
     * 패스워드 암호화
     */
    private String encodePassword(String password){
    	// 패스워드 암호화
		String ep = passwordEncoder.encode(password);
		return ep;
    }
    
	
    /**
     * 로그인
     */
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		MemberDto findMember = memberMapper.findById(memberDto);
		
		// 잘못된 패스워드 입력시 에러 발생
		if(findMember == null || !passwordEncoder.matches(memberDto.getMemberPw(), findMember.getMemberPw())) {
			throw new IllegalStateException("아이디 또는 패스워드가 틀렸습니다.");
		}
		
		// 패스워드가 일치할 경우 로그인
		return memberMapper.login(memberDto);
	}
	
	/**
     * 회원 정보 가져오기
     */
	@Override
	public MemberDto memberGetInfo(String memberId) throws Exception {
		return memberMapper.memberInfo(memberId);
	}
	
	/**
     * 회원 정보 변경
     */
	@Override
	@Transactional
	public void memberChangeInfo(MemberDto memberDto) throws Exception {
		if(memberDto.getMemberPw() != null) {
			memberDto.setMemberPw(encodePassword(memberDto.getMemberPw()));
		}
		memberMapper.changeMemberInfo(memberDto);
	}
	
	/**
     * 회원 탈퇴
     */
	@Override
	@Transactional
	public void remove(String memberId) throws Exception {
		memberMapper.removeMember(memberId);
	}
	
	/**
     * 비밀번호 잊어버렸을 때 비밀번호 변경
     */
	@Override
	public void change(String memberId, String email, String tel, String changePw) throws Exception {
		String findMemberId = memberMapper.checkBeforeChangePw(memberId, email, tel);
		if (findMemberId == null) {
			throw new IllegalStateException("등록된 회원이 없습니다.");
		}
		memberMapper.changePassword(memberId, encodePassword(changePw));
	}
}
