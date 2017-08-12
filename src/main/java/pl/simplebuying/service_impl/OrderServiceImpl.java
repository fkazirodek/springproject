package pl.simplebuying.service_impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Address;
import pl.simplebuying.model.Item;
import pl.simplebuying.model.Order;
import pl.simplebuying.model.Payment;
import pl.simplebuying.model.ShoppingCart;
import pl.simplebuying.model.User;
import pl.simplebuying.repository.ItemRepository;
import pl.simplebuying.repository.OrderRepository;
import pl.simplebuying.service.EmailService;
import pl.simplebuying.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	private ShoppingCart shoppingCart;
	private OrderRepository orderRepository;
	private ItemRepository itemRepository;
	private EmailService emailService;

	private Order order;

	@Autowired
	public OrderServiceImpl(ShoppingCart shoppingCart, OrderRepository orderRepository, ItemRepository itemRepository,
			EmailService emailService) {
		this.shoppingCart = shoppingCart;
		this.orderRepository = orderRepository;
		this.itemRepository = itemRepository;
		this.emailService = emailService;
	}

	@Override
	public void createOrder(Order order, Payment payment, User buyer) {
		order.setItems(shoppingCart.getItemsInCart());
		order.setUser(buyer);
		BigDecimal fullAmountOrder = shoppingCart.getAmount().add(order.getPayment().getDeliveryCosts());
		order.setAmountOfOrder(fullAmountOrder);
		order.setPayment(payment);
		this.order = order;
	}

	public boolean checkUserAddress(Address address) {
		return address == null ? false : true;
	}

	@Override
	public boolean checkAndSaveOrder(User buyer) {
		List<Item> itemsInCart = order.getItems();
		if (checkItemQuantity(itemsInCart)) {
			orderRepository.save(order);
			emailService.sendOrderEmailToBuyer(buyer, order);
			clearShoppingCart(itemsInCart);
			return true;
		} else {
			return false;
		}
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
