package pl.simplebuying.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.User;
import pl.simplebuying.model.VerificationToken;
import pl.simplebuying.repository.VerificationTokenRepository;

@Service
@Transactional
public class VerificationTokenService {

	private VerificationTokenRepository verificationTokenRepository;
	private EmailService emailService;

	@Autowired
	public VerificationTokenService(VerificationTokenRepository verificationTokenRepository, EmailService emailService) {
		this.verificationTokenRepository = verificationTokenRepository;
		this.emailService = emailService;
	}

	public void generateVerificationTokenAndSendEmail(User user) {
		VerificationToken token = new VerificationToken(UUID.randomUUID().toString(), user);
		verificationTokenRepository.save(token);
		emailService.sendEmailWithTokenToVerifiyUser(user, token);
	}

	public boolean verifyToken(String token) {
		VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
		if (verificationToken != null) {
			verificationToken.getUser().setEnabled(true);
			return true;
		} else {
			return false;
		}
	}
}
