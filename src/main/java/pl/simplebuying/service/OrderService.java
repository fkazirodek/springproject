package pl.simplebuying.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Address;
import pl.simplebuying.model.Item;
import pl.simplebuying.model.Order;
import pl.simplebuying.model.ShoppingCart;
import pl.simplebuying.model.User;
import pl.simplebuying.repository.ItemRepository;
import pl.simplebuying.repository.OrderRepository;

@Service
public class OrderService {

	private ShoppingCart shoppingCart;
	private OrderRepository orderRepository;
	private ItemRepository itemRepository;
	private EmailService emailService;

	private Order order;

	@Autowired
	public OrderService(ShoppingCart shoppingCart, OrderRepository orderRepository, ItemRepository itemRepository, EmailService emailService) {
		this.shoppingCart = shoppingCart;
		this.orderRepository = orderRepository;
		this.itemRepository = itemRepository;
		this.emailService = emailService;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public boolean checkUserAddress(Address address) {
		return address == null ? false : true;
	}

	public String getDateAsString() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");
		String formattedDate = date.format(formatter);
		return formattedDate;
	}

	public boolean saveOrder(User buyer) {
		List<Item> itemsInCart = shoppingCart.getItemsInCart();
		createOrder(buyer, itemsInCart);
		if (checkItemQuantity(itemsInCart)) {
			orderRepository.save(order);
			emailService.sendOrderEmailToBuyer(buyer, order);
			clearShoppingCart(itemsInCart);
			return true;
		} else {
			return false;
		}

	}

	private void createOrder(User buyer, List<Item> items) {
		order.setItems(items);
		order.setUser(buyer);
		order.setAmountOfOrder(shoppingCart.getAmount());
		order.setOrderDate(getDateAsString());
	}

	private boolean checkItemQuantity(List<Item> items) {
		boolean condition = false;
		for (Item item : items) {
			Item itemDB = itemRepository.findOne(item.getId());
			Integer quantity = itemDB.getQuantity();
			if (quantity > 0) {
				itemDB.setQuantity(quantity - 1);
				itemDB.getOrders().add(order);
				condition = true;
			} else {
				condition = false;
			}
		}
		return condition;
	}

	private void clearShoppingCart(List<Item> itemsInCart) {
		itemsInCart.clear();
		shoppingCart.setAmount(BigDecimal.ZERO);
	}

}
