package com.lmsportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {		
	
	@Bean
	public UserDetailsService getUserDetailsService()
	{
		 return new UserDetailsServiceImpl(); 
		
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration)throws Exception 
	{
		return configuration.getAuthenticationManager();
	}	

	@Bean
	public DaoAuthenticationProvider getDaoAuthProvider() 
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
				
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());

		return daoAuthenticationProvider;
	}
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http
		 .csrf().disable()
         .authorizeRequests()
        .antMatchers("/login", "/resources/**", "/css/**", "/fonts/**", "/img/**").permitAll()
        .antMatchers("/register", "/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
        .antMatchers("/users/addNew").permitAll()
         .antMatchers("/security/user/Edit/**").hasAuthority("ADMIN") 
         .antMatchers("/admin/**").hasAuthority("ADMIN") 
         .antMatchers("/teacher/**").hasAuthority("TEACHER") 
         .antMatchers("/student/**").hasAuthority("STUDENT") 
         .and()
         .formLogin()
         .loginPage("/login").permitAll()
         .loginProcessingUrl("/myLogin")
         .defaultSuccessUrl("/home")
         .and()
         .logout().invalidateHttpSession(true)
         .clearAuthentication(true)
         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
         .logoutSuccessUrl("/login").permitAll()
         .and()
         .exceptionHandling().accessDeniedPage("/accessDenied");      

		 http.authenticationProvider(getDaoAuthProvider());
		 
		DefaultSecurityFilterChain build = http.build();
		
		return build;
    }

	
}