package pl.simplebuying.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	
			.authorizeRequests()
				.antMatchers("/", "/register", "/cart/add", "/cart/delete", "/shoppingcart", "/search", "/itemslist", "/emailconfirm", "/password/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					.failureUrl("/loginerror")
					.defaultSuccessUrl("/")
					.permitAll()
			.and()
				.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.permitAll();

	}
	
}
