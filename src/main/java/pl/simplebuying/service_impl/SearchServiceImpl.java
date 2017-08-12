package pl.simplebuying.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Item;
import pl.simplebuying.repository.ItemRepository;
import pl.simplebuying.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	private ItemRepository itemRepository;
	
	@Autowired
	public SearchServiceImpl(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	@Override
	public List<Item> searchByItemName(String itemName) {
		List<Item> itemsByName = itemRepository.findByItemName(itemName);
		return itemsByName;
	}
	
}
