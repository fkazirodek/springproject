package pl.simplebuying.domain.order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.domain.cart.ShoppingCartService;
import pl.simplebuying.domain.item.Item;
import pl.simplebuying.domain.item.ItemService;
import pl.simplebuying.domain.order.OrderService;
import pl.simplebuying.domain.user.Address;
import pl.simplebuying.domain.user.User;
import pl.simplebuying.domain.verification.EmailService;

@Service
public class OrderService {

	private ShoppingCartService shoppingCartService;
	private OrderRepository orderRepository;
	private ItemService itemService;
	private EmailService emailService;

	private Order order;
	
	@Autowired
	public OrderService(ShoppingCartService shoppingCart, OrderRepository orderRepository, ItemService itemService,
			EmailService emailService) {
		this.shoppingCartService = shoppingCart;
		this.orderRepository = orderRepository;
		this.itemService = itemService;
		this.emailService = emailService;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public List<Order> findByUserID(Long id) {
		return orderRepository.findByUser_id(id);
	}

	public void createOrder(Order order, Payment payment, User buyer) {
		order.setItems(shoppingCartService.getItemsInCart());
		order.setUser(buyer);
		BigDecimal fullAmountOrder = shoppingCartService.getAmount().add(order.getPayment().getDeliveryCosts());
		order.setAmountOfOrder(fullAmountOrder);
		order.setPayment(payment);
		this.order = order;
	}

	public boolean checkUserAddress(Address address) {
		return address == null ? false : true;
	}

	public boolean checkAndSaveOrder(User buyer) {
		List<Item> itemsInCart = order.getItems();
		if (checkItemQuantity(itemsInCart)) {
			orderRepository.save(order);
			emailService.sendOrderEmailToBuyer(buyer, order);
			shoppingCartService.clearShoppingCart(itemsInCart);
			return true;
		} else {
			return false;
		}
	}

	private boolean checkItemQuantity(List<Item> items) {
		boolean condition = false;
		for (Item item : items) {
			Item itemDB = itemService.findOneItem(item.getId());
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

}
