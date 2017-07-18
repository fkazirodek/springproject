package pl.simplebuying.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = -4227913473217411398L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "item_name", nullable = false)
	private String itemName;
	@NotEmpty
	private String description;
	@NotNull
	private BigDecimal price;
	@NotNull
	private int quantity;
	@NotNull
	@ManyToOne
	private Category category;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User seller;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "item_buyer", 
			   joinColumns = @JoinColumn(name = "item_id"), 
			   inverseJoinColumns = @JoinColumn(name = "buyer_id", nullable = false))
	private User buyer;

	public Item() {
	}

	public Item(String itemName, String description, BigDecimal price, Category category, User seller) {
		super();
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.category = category;
		this.seller = seller;
	}

	public Long getId() {
		return id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

}
