package himedia.project.highfourm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfiguration {

	@Bean
	protected SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
					.requestMatchers("/", "/login", "/users/join", "/**").permitAll()
					//.requestMatchers("/users/**").hasRole("ADMIN")
					//.anyRequest().authenticated()
					);
		
		http.formLogin(formlogin -> 
			formlogin
			.loginPage("/")
			.defaultSuccessUrl("http://localhost:3000/orders", true)
			.failureUrl("/login?error=true")
			.loginProcessingUrl("/login")
			.usernameParameter("userId")
			.passwordParameter("password")
			);
		
		http.logout(logout -> 
			logout
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			);
		return http.build();
	}
	
	@Bean
	protected BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
