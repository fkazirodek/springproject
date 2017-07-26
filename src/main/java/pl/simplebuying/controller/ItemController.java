package pl.simplebuying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import pl.simplebuying.model.Category;
import pl.simplebuying.model.Item;
import pl.simplebuying.model.User;
import pl.simplebuying.service.ItemService;

@Controller
public class ItemController {

	private ItemService itemService;

	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping("/additem")
	public String addAttributes(Model model) {
		List<Category> categories = itemService.getAllCategories();
		Item item = new Item();
		model.addAttribute(item);
		model.addAttribute("categories", categories);
		return "add_item";
	}
	
	@PostMapping("/additem")
	public String saveItem(@ModelAttribute Item item, @ModelAttribute Category category, @SessionAttribute User user) {
		itemService.saveItem(item, category, user);
		return "redirect:/";
	}


	@GetMapping("/myitems")
	public String getMyItems(Model model, @SessionAttribute User user) {
		List<Item> items = itemService.getItemByUserId(user);
		model.addAttribute("items", items);
		return "my_items";
	}
}
