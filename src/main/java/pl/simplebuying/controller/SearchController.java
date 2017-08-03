package pl.simplebuying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("items", searchService.searchByItemName(item));
		model.addAttribute("searchName", item);
		return "items_list";
	}
	
}
