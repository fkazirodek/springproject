package pl.simplebuying.domain.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.simplebuying.domain.item.ItemService;
import pl.simplebuying.domain.order.Order;
import pl.simplebuying.domain.user.User;
import pl.simplebuying.domain.user.UserService;

@Service
@Transactional
public class ItemService {

	private ItemRepository itemRepository;
	private CategoryRepository categoryRepository;
	private UserService userService;

	@Autowired
	public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository, UserService userService) {
		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
		this.userService = userService;
	}
	
	public Item findOneItem(Long id) {
		return itemRepository.findOne(id);
	}
	
	public Item findItemByID(Long id) {
		return itemRepository.findOne(id);
	}
	
	public List<Item> findByItemName(String itemName) {
		return itemRepository.findByItemName(itemName);
	}

	public List<Item> getItemsByCategory(Long id) {
		return itemRepository.findByCategory_id(id);
	}
	
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	public void saveItem(Item item, Category category, User seller) {
		item.setCategory(category);
		item.setSeller(seller);
		category.getItems().add(item);
		itemRepository.save(item);
	}

	public List<Item> getItemsByPage(int page) {
		Page<Item> itemsByPage = itemRepository.findAll(new PageRequest(page, 10));
		List<Item> items = itemsByPage.getContent();
		return items;
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public List<Item> getUserItems(User user) {
		return itemRepository.findBySeller_id(user.getId());
	}

	public List<Item> getBoughtItemsByUser(User user) {
		List<Order> orders = userService.findByUserName(user.getUsername()).getOrders();
		List<Item> allItems = new ArrayList<>();
		for (Order order : orders) {
			List<Item> itemsByOrder = order.getItems();
			itemsByOrder.forEach(i -> allItems.add(i));
		}
		return allItems;
	}

	public List<Item> getSellItems(User user) {
		List<Item> items = getUserItems(user);
		List<Item> sellItems = new ArrayList<>();
		for (Item item : items) {
			List<Order> orders = item.getOrders();
			if (!orders.isEmpty()) {
				sellItems.add(item);
			}
		}
		return sellItems;
	}

}
