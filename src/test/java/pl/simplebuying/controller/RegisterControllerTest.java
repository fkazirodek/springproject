package pl.simplebuying.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import pl.simplebuying.model.User;
import pl.simplebuying.repository.UserRepository;
import pl.simplebuying.repository.UserRoleRepository;
import pl.simplebuying.service.UserService;
import pl.simplebuying.service.VerificationTokenService;
import pl.simplebuying.service_impl.UserServiceImpl;

public class RegisterControllerTest {

	RegisterController controller;
	UserService userService;
	
	UserRepository userRepository;
	UserRoleRepository roleRepository;
	VerificationTokenService verificationTokenService;
	
	MockMvc mockMvc;
	User user;
	
	@Before
	public void beforeMethod() {
		userRepository = mock(UserRepository.class);
		roleRepository = mock(UserRoleRepository.class);
		verificationTokenService = mock(VerificationTokenService.class);
		userService = spy(new UserServiceImpl(userRepository, roleRepository, verificationTokenService));
		controller = new RegisterController(userService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setSingleView(new InternalResourceView("templates/register.html")).build();
		user = new User("Jan", "Kowalski", "jkowalski", "jkowalski@wp.pl", "1234567");
	}
	
	@Test
	public void shouldShowHomePageAndAddModelAttribute() throws Exception {
		mockMvc.perform(get("/register"))
					.andExpect(view().name("register"))
					.andExpect(model().attributeExists("user"));
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
	
	@Test
	public void shouldInvokedMethodsWhenSaveUser() throws Exception {
		mockMvc.perform(post("/register")
						.param("firstName", "Jan")
						.param("lastName", "Kowalski")
						.param("username", "jkowalski")
						.param("email", "jkowalski@wp.pl")
						.param("password", "1234567"));
		
			verify(userService, times(1)).saveUserInDB(any(User.class));
			verify(roleRepository, times(1)).findByRole("ROLE_USER");
			verify(userRepository, times(1)).save(any(User.class));
			verify(verificationTokenService, times(1)).generateVerificationTokenAndSendEmail(any(User.class));
	}
}
