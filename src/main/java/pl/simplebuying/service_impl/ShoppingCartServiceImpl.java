package pl.simplebuying.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Item;
import pl.simplebuying.model.ShoppingCart;
import pl.simplebuying.service.ItemService;
import pl.simplebuying.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	private ShoppingCart cart;
	private ItemService itemService;

	@Autowired
	public ShoppingCartServiceImpl(ShoppingCart cart, ItemService itemService) {
		this.cart = cart;
		this.itemService = itemService;
	}

	@Override
	public List<Item> getItemsInCart() {
		return cart.getItemsInCart();
	}
	
	@Override
	public void addItemToCart(String itemId) {
		Long id = Long.decode(itemId);
		Item itemById = itemService.findItemByID(id);
		Integer quantity = itemById.getQuantity();
		if (quantity > 0) {
				getItemsInCart().add(itemById);
				cart.calculateAmount();
			}
		}

	@Override
	public void deleteItemFromCart(String itemId) {
		Long id = Long.decode(itemId);
		cart.getItemsInCart().removeIf(i -> i.getId().equals(id));
		cart.calculateAmount();

	}

}
