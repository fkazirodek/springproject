package pl.simplebuying.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Item;
import pl.simplebuying.model.ShoppingCart;

@Service
public class ShoppingCartService {

	private ShoppingCart cart;
	private ItemService itemService;

	@Autowired
	public ShoppingCartService(ShoppingCart cart, ItemService itemService) {
		this.cart = cart;
		this.itemService = itemService;
	}

	public void addItemToCart(String itemId) {
		Long id = Long.decode(itemId);
		Item itemById = itemService.findItemByID(id);
		Integer quantity = itemById.getQuantity();
		if (quantity > 0) {
			cart.getItemsInCart().add(itemById);
			cart.calculateAmount();
		}
	}

	public void deleteItemFromCart(String itemId) {
		Long id = Long.decode(itemId);
		cart.getItemsInCart().removeIf(i -> i.getId().equals(id));
		cart.calculateAmount();

	}

}
