package pl.simplebuying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Item;
import pl.simplebuying.repository.ItemRepository;

@Service
public class SearchService {

	private ItemRepository itemRepository;
	
	@Autowired
	public SearchService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	public List<Item> searchByItemName(String itemName) {
		List<Item> itemsByName = itemRepository.findByItemName(itemName);
		return itemsByName;
	}
	
}
