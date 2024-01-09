package br.com.treinamento.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.treinamento.security.filter.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    @Autowired
    private SecurityFilter securityFilter;
	
	@Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/alunos").permitAll()
				.requestMatchers("/health").permitAll()
				.requestMatchers("/alunos/*").permitAll())
			.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

	return http.build();
		
    }
	
}
