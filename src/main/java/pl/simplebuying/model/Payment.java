package pl.simplebuying.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "method")
	private String paymentMethod;
	@Column(name = "delivery_costs")
	private BigDecimal deliveryCosts;

	public Long getId() {
		return id;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public BigDecimal getDeliveryCosts() {
		return deliveryCosts;
	}

	public void setDeliveryCosts(BigDecimal deliveryCosts) {
		this.deliveryCosts = deliveryCosts;
	}

}
