package pl.simplebuying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.simplebuying.model.Item;
import pl.simplebuying.service.SearchService;

@Controller
public class SearchController {
	
	private SearchService searchService;
	
	@Autowired
	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}

	@GetMapping
	public String searchItem(@RequestParam String item, Model model) {
		List<Item> itemsByName = searchService.searchByItemName(item);
		model.addAttribute("items", itemsByName);
		return "index";
	}
	
}
