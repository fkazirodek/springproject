package pl.simplebuying.service_impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.simplebuying.model.Category;
import pl.simplebuying.model.Item;
import pl.simplebuying.model.Order;
import pl.simplebuying.model.Payment;
import pl.simplebuying.model.ShoppingCart;
import pl.simplebuying.model.User;
import pl.simplebuying.repository.ItemRepository;
import pl.simplebuying.repository.OrderRepository;
import pl.simplebuying.service.EmailService;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

	@InjectMocks
	ShoppingCart shoppingCart;
	@Mock
	OrderRepository orderRepository;
	@Mock
	ItemRepository itemRepository;
	@Mock
	EmailService emailService;

	OrderServiceImpl orderService;
	Order order;
	Payment payment;
	User buyer;
	Item item;
	Category category;
	
	@Before
	public void before() {
		orderService = spy(new OrderServiceImpl(shoppingCart, orderRepository, itemRepository, emailService));
		buyer = new User("Jan", "Kowalski", "jankowalski", "jakkowalski@mail.pl", "12345");
		createPayment();
		createOrder();
		createItemAndCategory();
		shoppingCart.getItemsInCart().add(item);
		shoppingCart.calculateAmount();
		orderService.createOrder(order, payment, buyer);
		
	}

	private void createOrder() {
		order = new Order();
		order.setPayment(payment);
	}

	private void createPayment() {
		payment = new Payment();
		payment.setDeliveryCosts(BigDecimal.valueOf(15));
	}
	
	private void createItemAndCategory() {
		category = new Category();
		category.setCategoryName("Telefony i Akcesoria");
		item = new Item("Samsung", "Samsung Galaxy S8", BigDecimal.valueOf(1000), category);
		item.setId(1L);
		item.setQuantity(2);
		item.setOrders(new ArrayList<>());
	}
	
	@Test
	public void whenCreateOrderThanOrderCreated() {
		assertEquals(order, orderService.getOrder());
	}
	
	@Test
	public void whenCreateOrderThanOrderNotNull() {
		assertNotNull(orderService.getOrder());
	}
	
	@Test
	public void userInOrderNotNull() {
		assertNotNull(orderService.getOrder().getUser());
	}
	
	@Test
	public void paymentInOrderNotNull() {
		assertNotNull(orderService.getOrder().getPayment());
	}
 	
	@Test
	public void itemsInOrderIsNotEmpty() {
		assertFalse(orderService.getOrder().getItems().isEmpty());
	}
	
	@Test
	public void amountOfOrderEqualsToAmount() {
		assertEquals(BigDecimal.valueOf(1015), orderService.getOrder().getAmountOfOrder());
	}
	
	@Test
	public void whenItemQuantityLessThan1ThanOrderCanNotBeSave() {
		when(itemRepository.findOne(item.getId())).thenReturn(item);
		item.setQuantity(0);
		assertFalse(orderService.checkAndSaveOrder(buyer));
	}
	
	@Test
	public void whenItemQuantityMoreThan1ThanOrderCanBeSave() {
		when(itemRepository.findOne(item.getId())).thenReturn(item);
		assertTrue(orderService.checkAndSaveOrder(buyer));
	}
	
	@Test
	public void whenSaveOrderThanItemQuantityDecrease() {
		when(itemRepository.findOne(item.getId())).thenReturn(item);
		orderService.checkAndSaveOrder(buyer);
		assertEquals(Integer.valueOf(1), item.getQuantity());
	}
	
	@Test
	public void whenClearShoppingCartThanShoppingCartIsEmpty() {
		when(itemRepository.findOne(item.getId())).thenReturn(item);
		assertTrue(orderService.checkAndSaveOrder(buyer));
		assertEquals(0, shoppingCart.getNumberOfItems());
	}
	
	@Test
	public void whenClearShoppingCartThanShoppingCartAmountIs0() {
		when(itemRepository.findOne(item.getId())).thenReturn(item);
		assertTrue(orderService.checkAndSaveOrder(buyer));
		assertEquals(BigDecimal.ZERO, shoppingCart.getAmount());
	}
}
