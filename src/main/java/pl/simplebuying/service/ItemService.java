package pl.simplebuying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Category;
import pl.simplebuying.model.Item;
import pl.simplebuying.model.User;
import pl.simplebuying.repository.CategoryRepository;
import pl.simplebuying.repository.ItemRepository;

@Service
public class ItemService {

	private ItemRepository itemRepository;
	private CategoryRepository categoryRepository;
	
	@Autowired
	public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository) {
		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public void saveItem(Item item, Category category, User seller) {
		item.setCategory(category);
		item.setSeller(seller);
		category.getItems().add(item);
		itemRepository.save(item);
	}

	public Page<Item> getAllItemsByPage(int page) {
		Page<Item> allItemsByPage = itemRepository.findAll(new PageRequest(page, 10));
		return allItemsByPage;
	}	
	
	public List<Category> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}
	
	public List<Item> getItemByUserId(User user) {
		List<Item> userItems = itemRepository.findBySeller_id(user.getId());
		return userItems;
	}
	
}
