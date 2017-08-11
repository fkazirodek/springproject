package pl.simplebuying.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.User;

@Service
public class PasswordService {

	private UserService userService;
	private EmailService emailService;

	@Autowired
	public PasswordService(UserService userService,EmailService emailService) {
		this.userService = userService;
		this.emailService = emailService;
	}

	public void sendPasswordToUser(String username) {
		User user = userService.findByUserName(username);
		emailService.sendEmailWithPassword(user.getEmail(), user.getPassword());
	}

}
