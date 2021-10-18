package com.springboot.moviescrud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source
	@Autowired
	private DataSource securityDataSource;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// use jdbc authentication ... oh yeah!!!

		auth.jdbcAuthentication().dataSource(securityDataSource)
				.authoritiesByUsernameQuery(
						"SELECT u.username, a.authority " +
								"FROM authorities a, users u " +
								"WHERE u.username = ? " +
								"AND u.authorities_id = a.authorities_id"
				);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{

		http.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/user/**").hasRole("USER")
			.and()
			.formLogin()
				.loginPage("/login-page")
				.loginProcessingUrl("/authenticateTheUser").usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/api/movies-directory")
				.permitAll()
				.and()
				.logout().permitAll();
			//.and()
			//.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
		
}






