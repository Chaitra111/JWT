package com.bridgelabz.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author Chaitra Ankolekar
 * Date : 10/07/2018
 * Purpose :TokenService is a class where all stuff related to JWT is happening
 */
@Service
public class TokenService {
	public static final String TOKEN_SECRET = "Chaitra";

	public String createToken(String userId) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			String token = JWT.create().withClaim("userName", userId.toString()).withClaim("createdAt", new Date())
					.sign(algorithm);
			System.out.println(token);
			return token;
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
			// log WRONG Encoding message
		} catch (JWTCreationException exception) {
			exception.printStackTrace();
			// log Token Signing Failed
		}
		return null;
	}

	public String getUserIdFromToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			return jwt.getClaim("userName").asString();
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
			// log WRONG Encoding message
			return null;
		} catch (JWTVerificationException exception) {
			exception.printStackTrace();
			// log Token Verification Failed
			return null;
		}
	}

	public boolean isTokenValid(String token) {
		String userId = this.getUserIdFromToken(token);
		return userId != null;
	}
}