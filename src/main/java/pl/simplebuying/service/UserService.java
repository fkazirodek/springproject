package pl.simplebuying.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.User;
import pl.simplebuying.repository.UserRepository;

@Service
public class UserService {

	private static final String DEFAULT_ROLE = "ROLE_USER";
	private UserRepository userRepository;
	private VerificationTokenService verificationTokenService;

	@Autowired
	public UserService(UserRepository userRepository, VerificationTokenService verificationTokenService) {
		this.userRepository = userRepository;
		this.verificationTokenService = verificationTokenService;
	}

	public void saveUserInDB(User user) {
		if (user.getRole() == null) {
			user.setRole(DEFAULT_ROLE);
		}
		userRepository.save(user);
		verificationTokenService.generateVerificationToken(user);
	}

	public User findByUserName(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}
	
	

	
	
}
