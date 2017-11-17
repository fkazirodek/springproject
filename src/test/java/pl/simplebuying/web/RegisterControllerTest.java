package pl.simplebuying.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import pl.simplebuying.domain.user.User;
import pl.simplebuying.domain.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration 
public class RegisterControllerTest {

	@MockBean
	RegisterController controller;
	@MockBean
	UserService userService;
	
	MockMvc mockMvc;
	User user;
	
	@Before
	public void beforeMethod() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setSingleView(new InternalResourceView("templates/register.html")).build();
		user = new User("Jan", "Kowalski", "jkowalski", "jkowalski@wp.pl", "1234567");
	}
	
	@Test
	public void shouldShowHomePageAndAddModelAttribute() throws Exception {
		mockMvc.perform(get("/register"))
					.andExpect(view().name("register"));
	}
	
	@Test
	public void shouldReturnToRegisterFormWhenNotValidUser() throws Exception {
		mockMvc.perform(post("/register")
						.param("firstName", "Jan")
						.param("lastName", "Kowalski")
						.param("username", "jkowalski")
						.param("email", "jkowalski@wp.pl")
						.param("password", ""))
					.andExpect(view().name("register"));
	}
	
}
