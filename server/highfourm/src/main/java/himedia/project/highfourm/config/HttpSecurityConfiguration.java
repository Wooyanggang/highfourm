package himedia.project.highfourm.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfiguration {

	@Bean
	protected SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
					.requestMatchers("/", "/login", "/users/join", "/**").permitAll()
					.requestMatchers("/users/**").hasRole("ADMIN")
					.anyRequest().authenticated()
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
		http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600L); //1시간
                return config;
            }
        }));
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
