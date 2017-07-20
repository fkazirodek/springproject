package pl.simplebuying.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pl.simplebuying.model.User;
import pl.simplebuying.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Użytkowanik o podanej nazwie nie istnieje");
		} 
		org.springframework.security.core.userdetails.User userDetails = 
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), addAuthorities(user.getRole()));
		return userDetails;
	}

	private Set<GrantedAuthority> addAuthorities(String userRole) {
        Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(userRole));
        return authorities;
	}
}
