package pl.scartout.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
			.antMatchers("/register").permitAll()
			.antMatchers("/contact").permitAll()
			.antMatchers("/filter").permitAll()
			.antMatchers("/product").permitAll()
			.antMatchers("/products").permitAll()
			.antMatchers("/search").permitAll()
			.antMatchers("/admin").hasAuthority("admin")
			.antMatchers("/productnew").hasAuthority("admin")
			.antMatchers("/productlist").hasAuthority("admin")
			.antMatchers("/productnew").hasAuthority("admin")
			.antMatchers("/ordersedit").hasAuthority("admin")
			.antMatchers("/images/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/plugins/**").permitAll()
			.antMatchers("/styles/**").permitAll()
			.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
			.permitAll()
		.and()
        .logout()
            .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll();
	}

}