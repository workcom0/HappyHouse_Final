package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.service.JwtServiceImpl;
import com.ssafy.happyhouse.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private final JwtServiceImpl jwtService;
	private final MemberService memberService;

	public MemberController(JwtServiceImpl jwtService, MemberService memberService) {
		this.jwtService = jwtService;
		this.memberService = memberService;
	}
	
	/*
	 * 회원 가입
	 */
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody MemberDto memberDto) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.debug("register : memberDto info : {}", memberDto);
		try {
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch(IllegalStateException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} catch(Exception e) {
			resultMap.put("message", "시스템 내부에 문제가 발생했습니다.");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/*
	 * 로그인
	 */
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto) {
		logger.debug("login : memberDto info : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginMember = memberService.login(memberDto);
			if (loginMember != null) {
				String accessToken = jwtService.createAccessToken("memberId", loginMember.getMemberId(), "access-token");// key, data,
				// refresh 토큰 발급
				String refreshToken = jwtService.createRefreshToken();
				resultMap.put("access-token", accessToken);
				resultMap.put("refrest-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (IllegalStateException e) {
			resultMap.put("message", e.getMessage());
			System.out.println(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/*
	 * 회원 정보 불러오기
	 */
	@GetMapping("/info/{memberId}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable String memberId,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				MemberDto memberDto = memberService.memberGetInfo(memberId);
				if(memberDto != null) {
					resultMap.put("memberInfo", memberDto);
					resultMap.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				}
				else {
					resultMap.put("message", memberId+"에 대한 회원 정보가 없습니다.");
					status = HttpStatus.BAD_REQUEST;
				}
				
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/*
	 * 회원 정보 수정
	 */
	@PutMapping("/info")
	public ResponseEntity<Map<String, Object>> chageInfo(@RequestBody MemberDto memberDto, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				memberService.memberChangeInfo(memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
				
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/*
	 * 회원 탈퇴
	 */
	@DeleteMapping("/remove/{memberId}")
	public ResponseEntity remove(@PathVariable String memberId,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		logger.debug("remove : memberId info : {}", memberId);
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				memberService.remove(memberId);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/*
	 * 비밀번호 찾기 - 올바른 접근이면 비밀번호를 변경해줌
	 */
	@PutMapping("/find")
	public ResponseEntity<Map<String, Object>> findPw(@RequestBody MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		logger.debug("find : memberId info : {}", memberDto);
		HttpStatus status = null;
		try {
			memberService.change(memberDto.getMemberId(), memberDto.getEmail(), memberDto.getTel(), memberDto.getMemberPw());
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
