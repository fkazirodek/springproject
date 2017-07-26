package pl.simplebuying.model;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCart {
	
	private List<Item> itemsInCart;
	private BigDecimal amount;
	
	public List<Item> getItemsInCard() {
		return itemsInCart;
	}
	public void setItemsInCard(List<Item> itemsInCart) {
		this.itemsInCart = itemsInCart;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal calculateAmount() {
		if(!itemsInCart.isEmpty()) {
			BigDecimal sum = BigDecimal.ZERO;
			itemsInCart.forEach((i) -> sum.add(i.getPrice()));
			return sum;
		} else {
			return BigDecimal.ZERO;
		}
	}
	
}
