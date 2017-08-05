package pl.simplebuying.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -7824921728774358375L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;
	@NotEmpty(message = "{pl.simplebuying.model.User.firstName.NotEmpty}")
	@Column(name = "firstname")
	private String firstName;
	@NotEmpty(message = "{pl.simplebuying.model.User.lastName.NotEmpty}")
	@Column(name = "lastname")
	private String lastName;
	@NotEmpty(message = "{pl.simplebuying.model.User.username.NotEmpty}")
	@Size(min = 4, max = 16, message = "{pl.simplebuying.model.User.username.Size}")
	@Column(nullable = false, unique = true)
	private String username;
	@Email(message = "{pl.simplebuying.model.User.email.Email}")
	@NotEmpty(message = "{pl.simplebuying.model.User.email.NotEmpty}")
	private String email;
	@NotEmpty(message = "{pl.simplebuying.model.User.password.NotEmpty}")
	@Min(value = 6, message = "{pl.simplebuying.model.User.password.Min}")
	private String password;
	@Embedded
	private Address address;
	@Column(name = "authorization", nullable = false)
	private String role;
	@Column(nullable = false)
	private boolean enabled;
	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
	private List<Item> userItems = new ArrayList<>();
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	public User() {
		this.enabled = false;
	}

	public User(String firstName, String lastName, String username, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Item> getUserItems() {
		return userItems;
	}

	public void setUserItems(List<Item> userItems) {
		this.userItems = userItems;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
