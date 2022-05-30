package com.ssafy.happyhouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable() // 기본 설정은 비인증 시 로그인 폼으로 이동시킴. REST API 이므로 사용 안함.
			.csrf().disable()		// REST API 이므로 csrf 보안이 필요 없음.
			.authorizeRequests()
				.antMatchers("/member/register", "/member/login", "/member/info/**",  "/member/remove/**", "/member/find").permitAll()
				.antMatchers("/house/sido", "/house/gugun", "/house/dong",  "/house/listByDong", "/house/listByName").permitAll()
				.antMatchers("/likedeal/**").permitAll()
				.antMatchers("/notice/**").permitAll()
				.antMatchers("/board/**").permitAll()
				.anyRequest().authenticated();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}