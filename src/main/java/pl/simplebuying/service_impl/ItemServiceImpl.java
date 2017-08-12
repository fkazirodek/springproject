package pl.simplebuying.service_impl;

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
import pl.simplebuying.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	private ItemRepository itemRepository;
	private CategoryRepository categoryRepository;
	private OrderRepository orderRepository;

	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, OrderRepository orderRepository) {
		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
		this.orderRepository = orderRepository;
	}

	@Override
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}
	
	@Override
	public Item findItemByID(Long id) {
		return itemRepository.findOne(id);
	}

	@Override
	public List<Item> getItemsByCategory(Long id) {
		return itemRepository.findByCategory_id(id);
	}

	@Override
	public void saveItem(Item item, Category category, User seller) {
		item.setCategory(category);
		item.setSeller(seller);
		category.getItems().add(item);
		itemRepository.save(item);
	}

	@Override
	public List<Item> getItemsByPage(int page) {
		Page<Item> itemsByPage = itemRepository.findAll(new PageRequest(page, 10));
		List<Item> items = itemsByPage.getContent();
		return items;
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Item> getUserItems(User user) {
		return itemRepository.findBySeller_id(user.getId());
	}

	@Override
	public List<Item> getBoughtItemsByUser(User user) {
		List<Order> orders = orderRepository.findByUser_id(user.getId());
		List<Item> allItems = new ArrayList<>();
		for (Order order : orders) {
			List<Item> itemsByOrder = order.getItems();
			itemsByOrder.forEach(i -> allItems.add(i));
		}
		return allItems;
	}

	@Override
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
