package br.com.treinamento.security.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Service
public class TokenService {
	
	@Value("${token.secret}")
	private String secret;
   
    public boolean validarToken(String token) {
    	try {
	    	Key secretKey = Keys.hmacShaKeyFor(secret.getBytes());
	    	Jws<Claims> claimsJws = Jwts.parserBuilder()
	    			.setSigningKey(secretKey)
	    			.build()
	    			.parseClaimsJws(token);
	    	Claims claims = claimsJws.getBody();
	        Date expirationDate = claims.getExpiration();
	        Date now = new Date();
	        
	        long diffInMilliseconds = expirationDate.getTime() - now.getTime();
	        long diffInHours = diffInMilliseconds / (60 * 60 * 1000);
	
	        return diffInHours < 2;
    	}catch (SignatureException e) {
			throw new SignatureException("Token inválido ou expirado!");
		}
          
    }
	
}
