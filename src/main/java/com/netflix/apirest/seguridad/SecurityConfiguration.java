package com.netflix.apirest.seguridad;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password(passwordEncoder().encode("123")).roles("USER")
			.and()
			.withUser("admin").password(passwordEncoder().encode("123")).roles("ADMIN", "USER");
	}
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
//                        .antMatchers("/manage/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET).permitAll()
                        .antMatchers(HttpMethod.POST, "/**").hasRole("USER")
                        .antMatchers(HttpMethod.PUT, "/**").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .httpBasic();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}