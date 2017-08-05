package pl.simplebuying.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Address;
import pl.simplebuying.model.User;
import pl.simplebuying.repository.UserRepository;

@Service
@Transactional
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
		verificationTokenService.generateVerificationTokenAndSendEmail(user);
	}

	public void updateAddress(Address address, User user) {
		User userDB = userRepository.findOne(user.getId());
		userDB.setAddress(address);
	}

	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

}
