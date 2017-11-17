package pl.simplebuying.web;

import static org.hamcrest.CoreMatchers.is;
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
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.simplebuying.domain.item.Category;
import pl.simplebuying.domain.item.Item;
import pl.simplebuying.domain.item.ItemService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTest {

	@Autowired
	WebApplicationContext ctx;
	
	@Mock
	ItemService itemService;
	
	MockMvc mockMvc;
	List<Item> itemList;
	
	@Before
	public void beforeMethod() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		itemList = new ArrayList<>();
		itemList.add(new Item("Samsung", "Sell smartphone", BigDecimal.valueOf(200), new Category()));
	}
	
	@Test
	public void shouldShowHomePage() throws Exception {
		when(itemService.getAllItems()).thenReturn(itemList);
		mockMvc.perform(get("/"))
					.andExpect(view().name("index"));
	}
	
	@Test
	public void shouldAddItemsToAtribute() throws Exception {
		when(itemService.getAllItems()).thenReturn(itemList);
		mockMvc.perform(get("/"))
					.andExpect(model().attributeExists("items"))
					.andExpect(model().attribute("items", is(itemList)));
	}

}
