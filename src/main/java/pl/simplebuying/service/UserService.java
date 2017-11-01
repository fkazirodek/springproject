package pl.simplebuying.service;

import pl.simplebuying.model.Address;
import pl.simplebuying.model.User;

public interface UserService {

	public boolean saveUserInDB(User user);
	public void updateAddress(Address address, User user);
	public User findByUserName(String username);
}
