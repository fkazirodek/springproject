package pl.simplebuying.service;

import pl.simplebuying.model.Order;
import pl.simplebuying.model.User;
import pl.simplebuying.model.VerificationToken;

public interface EmailService {

	public void sendEmail(String sendTo, String subject, String content);
	public void sendOrderEmailToBuyer(User buyer, Order order);
	public void sendEmailWithTokenToVerifiyUser(User user, VerificationToken token);
	public void sendEmailWithPassword(String email, String password);
}
