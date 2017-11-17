package pl.simplebuying.domain.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.domain.user.UserService;
import pl.simplebuying.domain.verification.VerificationTokenService;

@Service
@Transactional
public class UserService {

	private static final String DEFAULT_ROLE = "ROLE_USER";
	private UserRepository userRepository;
	UserRoleRepository roleRepository;
	private VerificationTokenService verificationTokenService;

	@Autowired
	public UserService(UserRepository userRepository, UserRoleRepository roleRepository, VerificationTokenService verificationTokenService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.verificationTokenService = verificationTokenService;
	}

	public boolean saveUserInDB(User user) {
		UserRole userRole = roleRepository.findByRole(DEFAULT_ROLE);
		user.getRoles().add(userRole);
		User userInDb = userRepository.save(user);
		verificationTokenService.generateVerificationTokenAndSendEmail(user);
		return userInDb != null ? true : false;
	}
	
	public void updateAddress(Address address, User user) {
		User userDB = userRepository.findOne(user.getId());
		userDB.setAddress(address);
	}

	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

}
