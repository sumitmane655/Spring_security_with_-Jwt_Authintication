package com.ecommerce.shopz.config;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecommerce.shopz.filter.JwtFilter;
import com.ecommerce.shopz.service.UserInfoDetailsService;



@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter authfilter;
	
	@Bean
	public UserDetailsService userDetailsService() {
//		UserDetails admin = org.springframework.security.core.userdetails.User.withUsername("sumit")
//				.password(encoder.encode("12345"))
//				.roles("ADMIN")
//				.build();
//		
//		UserDetails user = org.springframework.security.core.userdetails.User.withUsername("siddhesh")
//				.password(encoder.encode("56789"))
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin,user);
		
		return new 	UserInfoDetailsService();
	}
	
		
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		 return http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/user/new","/user/authenticate").permitAll()
				.and()
				.authorizeHttpRequests().requestMatchers("/user/**")
				.authenticated().and()
				.sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	            .authenticationProvider(authenticationProvider())
	            .addFilterBefore(authfilter, UsernamePasswordAuthenticationFilter.class).build();
				
			
				

	
	
	}
	
	@Bean
	public	 PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authinticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
