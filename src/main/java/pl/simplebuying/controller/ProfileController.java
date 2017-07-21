package pl.simplebuying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.simplebuying.model.User;
import pl.simplebuying.service.UserService;

@Controller
public class ProfileController {

	@Autowired
	private UserService userService;

	@GetMapping("/profile")
	public String redirect(Model model, Authentication auth) {
		User user = userService.findByUserName(auth.getName());
		model.addAttribute(user);
		return "profile";
	}
}
