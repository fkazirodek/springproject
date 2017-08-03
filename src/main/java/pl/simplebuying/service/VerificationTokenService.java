package pl.simplebuying.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.User;
import pl.simplebuying.model.VerificationToken;
import pl.simplebuying.repository.VerificationTokenRepository;

@Service
public class VerificationTokenService {

	private VerificationTokenRepository verificationTokenRepository;
	private EmailService emailService;

	@Autowired
	public VerificationTokenService(VerificationTokenRepository verificationTokenRepository,
			EmailService emailService) {
		this.verificationTokenRepository = verificationTokenRepository;
		this.emailService = emailService;
	}

	public void generateVerificationToken(User user) {
		VerificationToken token = new VerificationToken();
		String uuid = UUID.randomUUID().toString();
		token.setToken(uuid);
		token.setUser(user);
		verificationTokenRepository.save(token);
		emailService.sendEmailWithTokenToVerifiyUser(user, token);
	}

	public boolean verifyToken(User user, String token) {
		VerificationToken verificationToken = verificationTokenRepository.findByUser(user);
		if (verificationToken.getToken().equals(token)) {
			user.setEnabled(true);
			return true;
		} else {
			return false;
		}
	}
}
