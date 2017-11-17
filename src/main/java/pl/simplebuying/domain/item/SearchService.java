package pl.simplebuying.domain.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.domain.item.SearchService;

@Service
public class SearchService {

	private ItemService itemService;
	
	@Autowired
	public SearchService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	public List<Item> searchByItemName(String itemName) {
		List<Item> itemsByName = itemService.findByItemName(itemName);
		return itemsByName;
	}
	
}
