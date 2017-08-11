package pl.simplebuying.service;

import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import pl.simplebuying.model.Order;
import pl.simplebuying.model.User;
import pl.simplebuying.model.VerificationToken;

@Service
public class EmailService {

	private TemplateEngine templateEngine;
	private JavaMailSender mailSender;

	@Autowired
	public EmailService(TemplateEngine templateEngine, JavaMailSender mailSender) {
		this.templateEngine = templateEngine;
		this.mailSender = mailSender;
	}

	public void sendEmail(String sendTo, String subject, String content) {
		MimeMessage mail = mailSender.createMimeMessage();
		try {
			MimeMessageHelper msgHelper = new MimeMessageHelper(mail, true);
			msgHelper.setTo(sendTo);
			msgHelper.setSubject(subject);
			msgHelper.setText(content, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(mail);
	}
	
	public void sendOrderEmailToBuyer(User buyer, Order order) {
		Context context = new Context();
		Set<String> sellers = new HashSet<>();
		order.getItems().forEach(i -> sellers.add(i.getSeller().getUsername()));
		context.setVariable("date", order.getOrderDate());	
		context.setVariable("sellers", sellers);
		context.setVariable("items", order.getItems());
		context.setVariable("amount", order.getAmountOfOrder());
		context.setVariable("orderId", order.getId());
		context.setVariable("numberOfItems", order.getItems().size());
		String subject = "Potwierdzenie złóżenia zamówienia nr " + order.getId();
		String body = templateEngine.process("email", context);
		sendEmail(buyer.getEmail(), subject, body);
	}
	
	public void sendEmailWithTokenToVerifiyUser(User user, VerificationToken token) {
		Context context = new Context();
		String url = "http://localhost:8080/SimpleBuying/emailconfirm?token=" + token.getToken();
		context.setVariable("url", url);
		String subject = "Weryfikacja konta w serwisie SimpleBuying";
		String body = templateEngine.process("email_verification", context);
		sendEmail(user.getEmail(), subject, body);
	}
	
	public void sendEmailWithPassword(String email, String password) {
		Context context = new Context();
		context.setVariable("pass", password);
		String subject = "Twoje hasło do serwisu SimpleBuying";
		String body = templateEngine.process("email_password", context);
		sendEmail(email, subject, body);
	}
	
	
}
