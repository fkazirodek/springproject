package pl.simplebuying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.simplebuying.service.VerificationTokenService;

@Controller
public class VerificationController {

	@Autowired
	VerificationTokenService tokenService;
	
	@GetMapping("/emailconfirm")
	public String verify(@RequestParam String token, RedirectAttributes redirectAttribute) {
		if(tokenService.verifyToken(token)) {
			redirectAttribute.addFlashAttribute("message", "Pomyślnie zweryfikowano adres email. Zaloguj się ponownie aby potwierdzić zmiany");
		} else {
			redirectAttribute.addFlashAttribute("message", "Nie udało sie zweryfikować adresu email");
		}
		return "redirect:/";
	}
}
