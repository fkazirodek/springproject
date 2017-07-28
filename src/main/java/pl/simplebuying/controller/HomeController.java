package pl.simplebuying.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.simplebuying.model.Item;
import pl.simplebuying.model.ShoppingCart;
import pl.simplebuying.model.User;
import pl.simplebuying.service.ItemService;
import pl.simplebuying.service.UserService;

@Controller
public class HomeController {

	private ItemService itemService;
	private ShoppingCart shoppingCart;
	private UserService userService;

	@Autowired
	public HomeController(ItemService itemService, ShoppingCart shoppingCart, UserService userService) {
		this.itemService = itemService;
		this.shoppingCart = shoppingCart;
		this.userService = userService;
	}

	@GetMapping("/")
	public String home(Model model, HttpSession session, Authentication auth) {
		saveAuthenticatedUserInSession(session, auth);
		List<Item> items = itemService.getAllItems();
		session.setAttribute("cart", shoppingCart);
		model.addAttribute("items", items);
		return "index";
	}

	private void saveAuthenticatedUserInSession(HttpSession session, Authentication auth) {
		if(session.getAttribute("user") == null && auth != null && auth.isAuthenticated()) {
			User user = userService.findByUserName(auth.getName());
			session.setAttribute("user", user);
			session.setAttribute("address", user.getAddress());
		}
	}
}
