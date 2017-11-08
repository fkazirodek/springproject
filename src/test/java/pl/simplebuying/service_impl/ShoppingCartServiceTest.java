package pl.simplebuying.service_impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.simplebuying.model.Category;
import pl.simplebuying.model.Item;
import pl.simplebuying.model.ShoppingCart;
import pl.simplebuying.repository.CategoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceTest {

	@InjectMocks
	ShoppingCart cart;
	@Mock
	ItemServiceImpl itemService;
	@Mock
	CategoryRepository categoryRepository;
	
	ShoppingCartServiceImpl shoppingCartService;
	Item item;
	Item item2;
	Category category;

	@Before
	public void before() {
		shoppingCartService = spy(new ShoppingCartServiceImpl(cart, itemService));
		category = new Category();
		category.setCategoryName("Telefony i Akcesoria");
		item = new Item("Samsung", "Samsung Galaxy S8", BigDecimal.valueOf(1000), category);
		item.setId(1L);
		item.setQuantity(2);
		doReturn(item).when(itemService).findItemByID(1L);
		item2 = new Item("Samsung", "Samsung Galaxy S8", BigDecimal.valueOf(1000), category);
		item2.setId(2L);
		item2.setQuantity(2);
		doReturn(item2).when(itemService).findItemByID(2L);
	}

	@Test
	public void givenItemsInCartWhenInvokedgetItemsInCart() {
		shoppingCartService.addItemToCart(item.getId() + "");
		assertFalse(cart.getItemsInCart().isEmpty());
	}

	@Test
	public void whenAddItemToCartThenCartSizeIs1() {
		shoppingCartService.addItemToCart(item.getId() + "");
		assertEquals(1, cart.getNumberOfItems());
	}
	
	@Test
	public void whenAddTwoItemsToCartThenCartSizeIs2() {
		shoppingCartService.addItemToCart(item.getId() + "");
		shoppingCartService.addItemToCart(item2.getId() + "");
		assertEquals(2, cart.getNumberOfItems());
	}

	@Test
	public void cannotAddItemToCartWhenItemQuantityIs0() {
		item.setQuantity(0);
		shoppingCartService.addItemToCart(item.getId() + "");
		assertEquals(0, cart.getNumberOfItems());
	}

	@Test
	public void whenDeleteItemFromCartThanItemsInCartIs0() {
		shoppingCartService.addItemToCart(item.getId() + "");
		assertEquals(1, cart.getItemsInCart().size());
		shoppingCartService.deleteItemFromCart(item.getId() + "");
		assertEquals(0, cart.getNumberOfItems());
	}

	@Test
	public void add2ItemsToCartThanCalculateAmount() {
		shoppingCartService.addItemToCart(item.getId() + "");
		shoppingCartService.addItemToCart(item2.getId() + "");
		cart.calculateAmount();
		assertEquals(item.getPrice().add(item2.getPrice()), cart.getAmount());
	}
}
