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

	public void addItemToCart(String id) {
		Long id_L = Long.decode(id);
		Item itemById = itemService.findItemByID(id_L);
		cart.getItemsInCart().add(itemById);
		cart.calculateAmount();
	}

	public void deleteItemFromCart(String id) {
		Long id_L = Long.decode(id);
		cart.getItemsInCart().removeIf(i -> i.getId().equals(id_L));
	}

}
