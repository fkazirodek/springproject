package pl.simplebuying.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.simplebuying.model.Item;
import pl.simplebuying.model.ShoppingCart;
import pl.simplebuying.service.ItemService;

@Controller
public class HomeController {

	private ItemService itemService;
	private ShoppingCart shoppingCart;

	@Autowired
	public HomeController(ItemService itemService, ShoppingCart shoppingCart) {
		this.itemService = itemService;
		this.shoppingCart = shoppingCart;
	}

	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		List<Item> items = itemService.getAllItems();
		session.setAttribute("cart", shoppingCart);
		model.addAttribute("items", items);
		return "index";
	}
}
