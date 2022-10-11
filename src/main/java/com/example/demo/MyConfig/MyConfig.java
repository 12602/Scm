package com.example.demo.MyConfig;

import java.beans.JavaBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
	           auth.authenticationProvider(this.daoAuthenticationProvider());
	}
	@Bean
	public UserDetailsService getUserDetailsService()
	{
		return new UserDetailsServiceImpl();
		
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
                         DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
	        auth.setUserDetailsService(this.getUserDetailsService());
	        auth.setPasswordEncoder(this.passwordEncoder());
	        return auth;
	
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		 http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		 .antMatchers("/user/**").hasRole("USER")
		 .antMatchers("/**").permitAll().and().formLogin().
		 loginPage("/signin").loginProcessingUrl("/dologin")
		 .defaultSuccessUrl("/user/index")
		 .failureUrl("/loginfail")
		 .and().csrf().disable();
		
	}
	
	
	//configure method
	
	
	
}
