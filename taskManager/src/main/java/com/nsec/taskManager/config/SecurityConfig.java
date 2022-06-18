package com.nsec.taskManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.nsec.taskManager.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	PasswordEncoder encoder ;
	UserService userService ;
	
	@Autowired
	public SecurityConfig(PasswordEncoder encoder , UserService userService) {
		this.encoder = encoder ;
		this.userService = userService ;
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationBuilder) throws Exception {
		authenticationBuilder.authenticationProvider(authenticationProvider());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/addcourse" , "/addteacher").hasRole("ADMIN")
        .antMatchers("/addstudent").hasRole("TEACHER")
        .antMatchers("/coursedetails/{id}").hasAnyRole("ADMIN" , "TEACHER")
        .antMatchers("/dashboard").hasAnyRole("ADMIN","TEACHER","STUDENT")
        .and()
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        
        http.authorizeRequests()
        .regexMatchers("/login.*" , "/")
        .permitAll()
        .anyRequest().authenticated();
        
        http.formLogin().loginPage("/login").loginProcessingUrl("/login/auth").defaultSuccessUrl("/dashboard")
        .and().logout().logoutSuccessUrl("/").permitAll()
;	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	    return new CustomAccessDeniedHandler();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }
}
