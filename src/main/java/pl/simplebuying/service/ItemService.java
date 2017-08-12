package pl.simplebuying.service;

import java.util.List;

import pl.simplebuying.model.Category;
import pl.simplebuying.model.Item;
import pl.simplebuying.model.User;

public interface ItemService {

	public List<Item> getAllItems();
	public Item findItemByID(Long id);
	public List<Item> getItemsByCategory(Long id);
	public List<Item> getItemsByPage(int page);
	public void saveItem(Item item, Category category, User seller);
	public List<Item> getUserItems(User user);
	public List<Item> getBoughtItemsByUser(User user);
	public List<Item> getSellItems(User user);
	public List<Category> getAllCategories();
}
