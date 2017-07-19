package pl.simplebuying.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.User;
import pl.simplebuying.repository.UserRepository;

@Service
public class UserService {
	
	private static final String DEFAULT_ROLE = "ROLE_USER";
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void saveUserInDB(User user) {
		user.setRole(DEFAULT_ROLE);
		userRepository.save(user);
	}
}
