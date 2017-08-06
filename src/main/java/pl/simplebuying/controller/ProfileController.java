package pl.simplebuying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import pl.simplebuying.model.Address;
import pl.simplebuying.model.User;
import pl.simplebuying.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	private UserService userService;

	@Autowired
	public ProfileController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String profile() {
		return "profile";
	}

	@GetMapping("/update_address")
	public String editProfile(Model model) {
		model.addAttribute("address", new Address());
		return "edit_profile";
	}

	@PostMapping("/update_address")
	public String updateAddress(@ModelAttribute Address address, @SessionAttribute User user) {
		user.setAddress(address);
		userService.updateAddress(address, user);
		return "redirect:/profile";
	}
	
}
