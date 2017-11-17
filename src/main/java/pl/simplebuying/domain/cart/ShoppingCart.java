package pl.simplebuying.domain.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import pl.simplebuying.domain.item.Item;

@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Item> itemsInCart;
	private BigDecimal amount;

	public ShoppingCart() {
		itemsInCart = new ArrayList<>();
		this.amount = BigDecimal.ZERO;
	}

	public List<Item> getItemsInCart() {
		return itemsInCart;
	}

	public void setItemsInCart(List<Item> itemsInCart) {
		this.itemsInCart = itemsInCart;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getNumberOfItems() {
		return itemsInCart.isEmpty() ? 0 : itemsInCart.size();
	}

}
