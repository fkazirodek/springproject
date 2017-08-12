package pl.simplebuying.service;

import pl.simplebuying.model.User;

public interface VerificationTokenService {

	public void generateVerificationTokenAndSendEmail(User user);
	public boolean isVerifyToken(String token);
}
