package pl.simplebuying.domain.cart;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.domain.cart.ShoppingCartService;
import pl.simplebuying.domain.item.Item;
import pl.simplebuying.domain.item.ItemService;

@Service
public class ShoppingCartService {

	private ShoppingCart cart;
	private ItemService itemService;

	@Autowired
	public ShoppingCartService(ShoppingCart cart, ItemService itemService) {
		this.cart = cart;
		this.itemService = itemService;
	}

	public List<Item> getItemsInCart() {
		return cart.getItemsInCart();
	}
	
	public BigDecimal getAmount() {
		return cart.getAmount();
	}
	
	public void addItemToCart(String itemId) {
		Long id = Long.decode(itemId);
		Item itemById = itemService.findItemByID(id);
		Integer quantity = itemById.getQuantity();
		if (quantity > 0) {
				getItemsInCart().add(itemById);
				calculateAmount();
			}
		}

	public void deleteItemFromCart(String itemId) {
		Long id = Long.decode(itemId);
		cart.getItemsInCart().removeIf(i -> i.getId().equals(id));
		calculateAmount();

	}
	
	public void clearShoppingCart(List<Item> itemsInCart) {
		itemsInCart.clear();
		cart.setAmount(BigDecimal.ZERO);
	}
	
	public void calculateAmount() {
		if (!cart.getItemsInCart().isEmpty()) {
			cart.setAmount(BigDecimal.ZERO);
			cart.getItemsInCart().forEach((i) -> cart.setAmount(cart.getAmount().add(i.getPrice())));
		} else {
			cart.setAmount(BigDecimal.ZERO);
		}
	}

}
