package com.a302.webcuration.service;

import com.a302.webcuration.ExceptionHandler.InvalidTokenException;
import com.a302.webcuration.domain.Account.Account;
import com.a302.webcuration.domain.Account.AccountDto;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtService {
	
	public static final Logger logger = LoggerFactory.getLogger(JwtService.class);

	@Value("${token.signiturekey}")
	private String signature ;
	private Long expireMin = 60*24*5L;

//	로그인 성공시 사용자 정보를 기반으로 JWTToken을 생성하여 반환.
	public String create(Long id,String email) {
		JwtBuilder jwtBuilder = Jwts.builder();
//		JWT Token = Header + Payload + Signature
		logger.info("create jwttoken id "+id);
//		Header 설정
		jwtBuilder.setHeaderParam("typ", "JWT"); // 토큰의 타입으로 고정 값.

		logger.info(signature);
//		Payload 설정
		jwtBuilder
			.setSubject("로그인토큰") // 토큰의 제목 설정
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin)) // 유효기간 설정
			.claim("accountId", id)
		.claim("accountEmail",email); // 담고 싶은 정보 설정.
		
//		signature 설정
		jwtBuilder.signWith(SignatureAlgorithm.HS256, signature.getBytes());
		
//		마지막 직렬화 처리
		String jwt = jwtBuilder.compact();
		logger.info("jwt : {}", jwt);
		return jwt;
	}
	
//	전달 받은 토큰이 제대로 생성된것이니 확인 하고 문제가 있다면 RuntimeException을 발생.
	public void checkValid(String jwt) {
//		예외가 발생하지 않으면 OK
		Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
	}
	
//	JWT Token을 분석해서 필요한 정보를 반환.
	public Map<String, Object> get(String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new RuntimeException();
        }

        logger.info("claims : {}", claims);
        // Claims는 Map의 구현체이다.
        return claims.getBody();
    }

	public Long getAccountId(String tokenKey)
	{
		String token=tokenKey.substring(7);

		Jws<Claims> claims = null;

		try {
			claims = Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(token);
			logger.info("AccountId :"+claims.getBody().get("accountId"));
		} catch (final Exception e) {
			logger.error(e.getMessage());
			throw new InvalidTokenException("정상적인 토큰 값이 아닙니다.");
		}
		return  Long.parseLong(claims.getBody().get("accountId").toString());
	}
	public String getAccountEmail(String tokenKey)
	{
		String token=tokenKey.substring(7);

		Jws<Claims> claims = null;

		try {
			claims = Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(token);
			logger.info("accountEmail :"+claims.getBody().get("accountEmail"));
		} catch (final Exception e) {
			logger.error(e.getMessage());
			throw new InvalidTokenException("정상적인 토큰 값이 아닙니다.");
		}
		return  claims.getBody().get("accountEmail").toString();
	}
}
