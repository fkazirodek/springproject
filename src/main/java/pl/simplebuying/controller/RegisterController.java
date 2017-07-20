package pl.simplebuying.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.simplebuying.model.User;
import pl.simplebuying.service.UserService;

@Controller("/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String redirect(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping
	public String registerUser(@Valid @ModelAttribute User user, BindingResult results) {
		if (results.hasErrors()) {
			return "register";
		} else {
			userService.saveUserInDB(user);
			return "redirect:/login";
		}
	}

}
