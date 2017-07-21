package pl.simplebuying.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String authenticate() {
		return "login";
	}
	
	@GetMapping("/loginerror")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "login";
	  }


}
