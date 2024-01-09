package br.com.treinamento.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.treinamento.exception.ValidationJWTException;
import br.com.treinamento.security.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	TokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var tokenJWT = recuperarToken(request);
		
		if(request.getRequestURI().toString().equalsIgnoreCase("/health")) {
			filterChain.doFilter(request, response);
		} else if (tokenJWT != null && tokenService.validarToken(tokenJWT)) {
			filterChain.doFilter(request, response);
        } else {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        	
		
	}
	
	private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }

}
