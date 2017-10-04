package pl.simplebuying.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = -4227913473217411398L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item")
	private Long id;
	@NotEmpty(message = "{pl.simplebuying.model.Item.itemName.NotEmpty}")
	@Column(name = "item_name", nullable = false)
	private String itemName;
	@NotEmpty(message = "{pl.simplebuying.model.Item.description.NotEmpty}")
	private String description;
	@NotNull(message = "{pl.simplebuying.model.Item.price.NotNull}")
	private BigDecimal price;
	@NotNull(message = "{pl.simplebuying.model.Item.quantity.NotNull}")
	private Integer quantity;
	@NotNull
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name= "seller_id", nullable = false)
	private User seller;
	@ManyToMany
	private List<Order> orders;

	public Item() {
	}

	public Item(String itemName, String description, BigDecimal price, Category category) {
		super();
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
