package com.ssafy.happyhouse.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.happyhouse.dto.MemberDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService{
	private static final String SALT = "ssafySecret";
	private static final int ACCESS_TOKEN_EXPIRE_MINUTES = 60;				// 1시간 후 만료
	private static final int REFRESH_TOKEN_EXPIRE_MINUTES = 60 * 24 * 14;	// 14일 후 만료
	
	// jwt access 토큰 생성
	@Override
	public <T> String createAccessToken(String key, T data, String subject) {
		String jwt = Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("regDate", System.currentTimeMillis())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * ACCESS_TOKEN_EXPIRE_MINUTES)).setSubject(subject)
				.claim(key, data).signWith(SignatureAlgorithm.HS256, this.generateKey()).compact();
		return jwt;
	}
	
	// jwt refresh 토큰 생성
    public <T> String createRefreshToken() {
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)	// 발행시간
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * REFRESH_TOKEN_EXPIRE_MINUTES))
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact();
    }
	
	// 암호 키 생성
	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return key;
	}

	//	전달 받은 토큰이 제대로 생성된것인지 확인 하고 문제가 있다면 UnauthorizedException을 발생.
	@Override
	public boolean isUsable(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * Access-Token이 만료기간 10분 전이면 Access-Token 재 발급해주기
     */
//	public void expiredCheck(MemberDto memberDto) throws Exception {
//		MemberDto findMember = memberMapper.memberInfo(memberDto.getMemberId());
//		
//		// 잘못된 패스워드 입력시 에러 발생
//		if(!passwordEncoder.matches(memberDto.getMemberPw(), findMember.getMemberPw())) {
//			throw new IllegalStateException("아이디 또는 패스워드가 틀렸습니다.");
//		}
//		
//		// 패스워드가 일치할 경우 로그인
//		return memberMapper.login(memberDto);
//	}

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String jwt = request.getHeader("access-token");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> value = claims.getBody();
		return value;
	}

	@Override
	public String getMemberId() {
		return (String) this.get("member").get("memberId");
	}

}
