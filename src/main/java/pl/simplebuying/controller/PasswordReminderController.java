package pl.simplebuying.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.simplebuying.service.PasswordService;

@Controller
public class PasswordReminderController {

	PasswordService passwordService;

	public PasswordReminderController(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	@GetMapping("/password/remind")
	public String reminder() {
		return "remind_password";
	}
	
	@PostMapping("/password/remind")
	public String sendReminder(@RequestParam String username, RedirectAttributes redirect) {
		passwordService.sendPasswordToUser(username);
		redirect.addFlashAttribute("message", "Nowe hasło zostało wysłane na maila");
		return "redirect:/";
	}
}
