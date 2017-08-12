package pl.simplebuying.service;

import pl.simplebuying.model.Address;
import pl.simplebuying.model.Order;
import pl.simplebuying.model.Payment;
import pl.simplebuying.model.User;

public interface OrderService {

	public void createOrder(Order order, Payment payment, User buyer);
	public boolean checkAndSaveOrder(User buyer);
	public boolean checkUserAddress(Address address);
}
