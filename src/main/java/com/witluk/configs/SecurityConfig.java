package com.witluk.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.witluk.handlers.MyAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(this.dataSource)
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(rolesQuery);
	}
	
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
	
}
