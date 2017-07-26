package pl.simplebuying.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable=true)
	private String city;
	@Column(nullable=true)
	private String street;
	@Column(nullable=true)
	private String zipCode;
	
	public Address() {}

	public Address(String city, String street, String zipCode) {
		super();
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}
