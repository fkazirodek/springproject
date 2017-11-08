package pl.simplebuying.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.simplebuying.model.Address;
import pl.simplebuying.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	User user;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void beforeMethod() {
		user = new User("Firstname", "Lastname", "user", "user@email.pl", "123456");
	}
	
	@Test
	public void shouldSaveUserInDB() {
		assertNotNull(userRepository.save(user));
	}

	@Test
	@Transactional
	public void shouldSaveAndFindUserInDB() {
		userRepository.save(user);
		User userFromBD = userRepository.findByUsername(user.getUsername());
		assertEquals(user, userFromBD);
	}
	
	@Test
	@Transactional
	public void shouldReturnNumberOfUsersSavedInDB() {
		userRepository.save(user);
		assertEquals(1, userRepository.count());
	}
	
	@Test
	@Transactional
	public void shouldNotSaveUserIfUsernameExists() {
		User user2 = new User("John", "Smith", "user", "jSmith@email.pl", "123456");
		userRepository.save(user);
		exception.expect(DataIntegrityViolationException.class);
		userRepository.save(user2);
	}
	
	@Test
	@Transactional
	public void shouldNotSaveUserIfPasswordIsEmpty() {
		User user2 = new User("John", "Smith", "jSmith", "jSmith@email.pl", "");
		exception.expect(ConstraintViolationException.class);
		userRepository.save(user2);
	}
	
	@Test
	@Transactional
	public void shouldSetAddress() {
		User saveUser = userRepository.save(user);
		saveUser.setAddress(new Address("Warsow", "AL. Jerozolimske", "00-000"));
		assertNotNull(userRepository.findByUsername(user.getUsername()).getAddress());
	}
	
	
}
