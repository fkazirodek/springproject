package pl.simplebuying.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Category;
import pl.simplebuying.model.Item;
import pl.simplebuying.model.Order;
import pl.simplebuying.model.User;
import pl.simplebuying.repository.CategoryRepository;
import pl.simplebuying.repository.ItemRepository;
import pl.simplebuying.repository.OrderRepository;

@Service
public class ItemService {

	private ItemRepository itemRepository;
	private CategoryRepository categoryRepository;
	private OrderRepository orderRepository;
	
	@Autowired
	public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository, OrderRepository orderRepository) {
		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
		this.orderRepository = orderRepository;
	}
	
	
	public List<Item> getAllItems() {
		List<Item> items = itemRepository.findAll();
		return items;
	}
	
	public Item findItemByID(Long id) {
		Item item = itemRepository.findOne(id);
		return item;
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
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}
	
	public List<Item> getUserItems(User user) {
		List<Item> userItems = itemRepository.findBySeller_id(user.getId());
		return userItems;
	}
	
	public List<Item> getBoughtItemsByUser(User user) {
		List<Order> orders = orderRepository.findByUser_id(user.getId());
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
			if(orders != null) {
				sellItems.add(item);
			}
		}
		return sellItems;
	}
	
}
