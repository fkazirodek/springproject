package pl.simplebuying.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -7824921728774358375L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@NotNull
	@Column(nullable = false, unique = true, length = 16)
	private String username;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	@Embedded
	private Address address;
	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Item> userItems = new ArrayList<>();
	@OneToMany(mappedBy = "buyer")
	private List<Item> boughtItems = new ArrayList<>();

	public User() {
	}

	public User(String firstName, String lastName, String username, String email, String password, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = address;	
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Item> getUserItems() {
		return userItems;
	}

	public void setUserItems(List<Item> userItems) {
		this.userItems = userItems;
	}

	public List<Item> getBoughtItems() {
		return boughtItems;
	}

	public void setBoughtItems(List<Item> boughtItems) {
		this.boughtItems = boughtItems;
	}

	


}