package pl.simplebuying.service_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.User;
import pl.simplebuying.service.EmailService;
import pl.simplebuying.service.PasswordService;
import pl.simplebuying.service.UserService;

@Service
public class PasswordServiceImpl implements PasswordService {

	private UserService userService;
	private EmailService emailService;

	@Autowired
	public PasswordServiceImpl(UserService userService, EmailService emailService) {
		this.userService = userService;
		this.emailService = emailService;
	}

	@Override
	public void sendPasswordToUser(String username) {
		User user = userService.findByUserName(username);
		emailService.sendEmailWithPassword(user.getEmail(), user.getPassword());
	}

}
