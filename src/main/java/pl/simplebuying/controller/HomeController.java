package pl.simplebuying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.simplebuying.model.Item;
import pl.simplebuying.service.ItemService;

@Controller
public class HomeController {

	private ItemService itemService;

	@Autowired
	public HomeController(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@GetMapping("/")
	public String home(Model model) {
		List<Item> items = itemService.getAllItems();
		model.addAttribute("items", items);
		return "index";
	}
}
