package pl.simplebuying.service;

import java.util.List;

import pl.simplebuying.model.Item;

public interface ShoppingCartService {

	public List<Item> getItemsInCart();
	public void addItemToCart(String itemId);
	public void deleteItemFromCart(String itemId);
}
