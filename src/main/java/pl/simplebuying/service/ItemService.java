package pl.simplebuying.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import pl.simplebuying.model.Item;
import pl.simplebuying.repository.ItemRepository;


public class ItemService {

	@Autowired
	private static ItemRepository itemRepository;
	
	public static Page<Item> getAllItemsByPage(int page) {
		Page<Item> allItemsByPage = itemRepository.findAll(new PageRequest(page, 10));
		return allItemsByPage;
	}	
}
