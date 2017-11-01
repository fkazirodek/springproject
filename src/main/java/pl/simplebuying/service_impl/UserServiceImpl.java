package pl.simplebuying.service_impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Address;
import pl.simplebuying.model.User;
import pl.simplebuying.model.UserRole;
import pl.simplebuying.repository.UserRepository;
import pl.simplebuying.repository.UserRoleRepository;
import pl.simplebuying.service.UserService;
import pl.simplebuying.service.VerificationTokenService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final String DEFAULT_ROLE = "ROLE_USER";
	private UserRepository userRepository;
	UserRoleRepository roleRepository;
	private VerificationTokenService verificationTokenService;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserRoleRepository roleRepository, VerificationTokenService verificationTokenService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.verificationTokenService = verificationTokenService;
	}

	@Override
	public boolean saveUserInDB(User user) {
		UserRole userRole = roleRepository.findByRole(DEFAULT_ROLE);
		user.getRoles().add(userRole);
		User userInDb = userRepository.save(user);
		verificationTokenService.generateVerificationTokenAndSendEmail(user);
		return userInDb != null ? true : false;
	}

	@Override
	public void updateAddress(Address address, User user) {
		User userDB = userRepository.findOne(user.getId());
		userDB.setAddress(address);
	}

	@Override
	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

}
