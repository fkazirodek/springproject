package pl.simplebuying.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public String getAuthUser(Model model, Authentication auth, HttpSession session) {
		User user = userService.findByUserName(auth.getName());
		session.setAttribute("user", user);
		session.setAttribute("address", user.getAddress());
		return "profile";
	}

	@GetMapping("/update_address")
	public String editProfile(Model model) {
		Address address = new Address();
		model.addAttribute(address);
		return "edit_profile";
	}

	@PostMapping("/update_address")
	public String updateAddress(@ModelAttribute Address address, @SessionAttribute User user) {
		user.setAddress(address);
		userService.saveUserInDB(user);
		return "redirect:/profile";
	}
	
}
