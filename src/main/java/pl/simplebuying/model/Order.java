package pl.simplebuying.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order")
	private Long id;
	@Column(nullable = true)
	private String orderDetails;
	@Column(name = "amount")
	private BigDecimal amountOfOrder;
	@Column(name = "order_date")
	private LocalDate orderDate;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToMany
	@JoinTable(name = "order_item", 
		  	   joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id_order"), 
			   inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id_item"))
	private List<Item> items;

	public Order() {
		orderDate = LocalDate.now();
	}

	public Order(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Long getId() {
		return id;
	}

	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public BigDecimal getAmountOfOrder() {
		return amountOfOrder;
	}

	public void setAmountOfOrder(BigDecimal amountOfOrder) {
		this.amountOfOrder = amountOfOrder;
	}
	
	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
