package pl.simplebuying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import pl.simplebuying.model.Order;
import pl.simplebuying.service.ShoppingCartService;

@Controller
public class CartController {

	private ShoppingCartService shoppingCartService;

	@Autowired
	public CartController(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	@PostMapping("/cart/add")
	public String addItem(@RequestParam String id, @RequestHeader(value = "referer", required = false) String header) {
		shoppingCartService.addItemToCart(id);
		return "redirect:" + header;
	}

	@GetMapping("/cart/delete")
	public String deleteItem(@RequestParam String id,
			@RequestHeader(value = "referer", required = false) String header) {
		shoppingCartService.deleteItemFromCart(id);
		return "redirect:" + header;
	}

	@GetMapping("/shoppingcart")
	public String shoppingCart(Model model) {
		if (shoppingCartService.getItemsInCart().isEmpty()) {
			model.addAttribute("message", "Twój koszyk z zakupami jest pusty");
			return "index";
		} else {
			model.addAttribute("order", new Order());
			return "shopping_cart";
		}
	}

}
