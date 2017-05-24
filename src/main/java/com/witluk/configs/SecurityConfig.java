package com.witluk.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.witluk.handlers.MyAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        		.antMatchers("/", "/about").permitAll()
        		.antMatchers("/librarian/**").hasAnyRole("ADMIN")
                .antMatchers("/reader/**").hasAnyRole("USER")
                .anyRequest().authenticated()
        	.and()
        		.formLogin().successHandler(myAuthenticationSuccessHandler).loginPage("/login").permitAll()
        	.and()
        		.logout().permitAll()
        	.and()
        		.exceptionHandling().accessDeniedPage("/403");
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("reader").password("pass").roles("USER")
                .and()
                .withUser("librarian").password("pass").roles("ADMIN");
    }
	
}
