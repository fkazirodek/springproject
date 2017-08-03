package pl.simplebuying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import pl.simplebuying.model.User;
import pl.simplebuying.service.VerificationTokenService;

@Controller
public class VerificationController {

	@Autowired
	VerificationTokenService tokenService;
	
	@GetMapping("/emailconfirm")
	public String verify(@RequestParam String token, @SessionAttribute User user, Model model) {
		if(tokenService.verifyToken(user, token)) {
			model.addAttribute("message", "Pomyślnie zweryfikowano adres email");
		} else {
			model.addAttribute("message", "Nie udało sie zweryfikować adresu email");
		}
		return "index";
	}
}
