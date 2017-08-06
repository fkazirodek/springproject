package pl.simplebuying.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.simplebuying.model.User;
import pl.simplebuying.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private UserService userService;

	@Autowired
	public RegisterController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String addAttribute(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping
	public String registerUser(@Valid @ModelAttribute User user, BindingResult results) {
		if (results.hasErrors()) {
			return "register";
		} 
		userService.saveUserInDB(user);
		return "redirect:/login";
	}

}
