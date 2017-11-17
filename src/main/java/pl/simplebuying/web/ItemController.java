package pl.simplebuying.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import pl.simplebuying.domain.item.Category;
import pl.simplebuying.domain.item.Item;
import pl.simplebuying.domain.item.ItemService;
import pl.simplebuying.domain.user.User;

@Controller
public class ItemController {

	private ItemService itemService;

	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping("/item/add")
	public String addAttributes(Model model) {
		model.addAttribute("item", new Item());
		model.addAttribute("categories", itemService.getAllCategories());
		return "add_item";
	}

	@PostMapping("/item/add")
	public String saveItem(@Valid @ModelAttribute Item item, BindingResult result, @ModelAttribute Category category, @SessionAttribute User user, Model model) {
		if (result.hasErrors()) {
			return "add_item";
		} else {
			itemService.saveItem(item, category, user);
			return "redirect:/";
		}
	}

	@GetMapping("/items/my")
	public String getMyItems(Model model, @SessionAttribute User user) {
		model.addAttribute("items", itemService.getUserItems(user));
		return "my_items";
	}

	@GetMapping("/items/bought")
	public String boughtItems(@SessionAttribute User user, Model model) {
		model.addAttribute("items", itemService.getBoughtItemsByUser(user));
		return "bought_items";
	}

	@GetMapping("/items/sell")
	public String sellItems(@SessionAttribute User user, Model model) {
		model.addAttribute("items", itemService.getSellItems(user));
		return "sell_items";
	}
	
	@GetMapping("/itemslist")
	public String itemsList(@RequestParam Long category, Model model) {
		model.addAttribute("items", itemService.getItemsByCategory(category));
		return "items_list";
	}

}
