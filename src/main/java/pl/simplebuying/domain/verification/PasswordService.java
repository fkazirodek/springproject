package pl.simplebuying.domain.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.domain.user.User;
import pl.simplebuying.domain.user.UserService;
import pl.simplebuying.domain.verification.PasswordService;

@Service
public class PasswordService {

	private UserService userService;
	private EmailService emailService;

	@Autowired
	public PasswordService(UserService userService, EmailService emailService) {
		this.userService = userService;
		this.emailService = emailService;
	}

	public void sendPasswordToUser(String username) {
		User user = userService.findByUserName(username);
		emailService.sendEmailWithPassword(user.getEmail(), user.getPassword());
	}

}
