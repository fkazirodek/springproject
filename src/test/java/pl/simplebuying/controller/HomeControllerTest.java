package pl.simplebuying.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pl.simplebuying.model.Category;
import pl.simplebuying.model.Item;
import pl.simplebuying.model.ShoppingCart;
import pl.simplebuying.repository.CategoryRepository;
import pl.simplebuying.repository.ItemRepository;
import pl.simplebuying.repository.OrderRepository;
import pl.simplebuying.service.ItemService;
import pl.simplebuying.service.UserService;
import pl.simplebuying.service_impl.ItemServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration 
public class HomeControllerTest {

	HomeController homeController;
	
	ItemService itemService;
	ShoppingCart shoppingCart;
	UserService userService;
	
	ItemRepository itemRepository;
	CategoryRepository categoryRepository;
	OrderRepository orderRepository;
	
	MockMvc mockMvc;
	
	List<Item> itemList;
	
	@Before
	public void beforeMethod() {
		shoppingCart = mock(ShoppingCart.class);
		itemRepository = mock(ItemRepository.class);
		itemService = new ItemServiceImpl(itemRepository, categoryRepository, orderRepository);
		homeController = new HomeController(itemService, shoppingCart, userService);
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
		itemList = new ArrayList<>();
		itemList.add(new Item("Samsung", "Sell smartphone", BigDecimal.valueOf(200), new Category()));
	}
	
	@Test
	public void shouldShowHomePage() throws Exception {
		when(itemRepository.findAll()).thenReturn(itemList);
		mockMvc.perform(get("/"))
					.andExpect(view().name("index"));
	}
	
	@Test
	public void shouldAddItemsToAtribute() throws Exception {
		when(itemRepository.findAll()).thenReturn(itemList);
		mockMvc.perform(get("/"))
					.andExpect(model().attributeExists("items"))
					.andExpect(model().attribute("items", is(itemList)));
	}

}
