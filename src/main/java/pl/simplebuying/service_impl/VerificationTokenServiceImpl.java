package pl.simplebuying.service_impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.User;
import pl.simplebuying.model.VerificationToken;
import pl.simplebuying.repository.VerificationTokenRepository;
import pl.simplebuying.service.EmailService;
import pl.simplebuying.service.VerificationTokenService;

@Service
@Transactional
public class VerificationTokenServiceImpl implements VerificationTokenService {

	private VerificationTokenRepository verificationTokenRepository;
	private EmailService emailService;

	@Autowired
	public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository, EmailService emailService) {
		this.verificationTokenRepository = verificationTokenRepository;
		this.emailService = emailService;
	}

	@Override
	public void generateVerificationTokenAndSendEmail(User user) {
		VerificationToken token = new VerificationToken(UUID.randomUUID().toString(), user);
		verificationTokenRepository.save(token);
		emailService.sendEmailWithTokenToVerifiyUser(user, token);
	}

	@Override
	public boolean isVerifyToken(String token) {
		VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
		if (verificationToken != null) {
			verificationToken.getUser().setEnabled(true);
			return true;
		} else {
			return false;
		}
	}
}
