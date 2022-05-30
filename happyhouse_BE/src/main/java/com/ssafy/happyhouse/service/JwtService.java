package com.ssafy.happyhouse.service;

import java.util.Map;

public interface JwtService {
	<T> String createAccessToken(String key, T data, String subject);
	<T> String createRefreshToken();
	Map<String, Object> get(String key);
	String getMemberId();
	boolean isUsable(String jwt);
}
