package pl.simplebuying.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String text;
	private LocalDate date;
	@NotNull
	private boolean positive;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Comment() {
		date = LocalDate.now();
	}

	public Comment(String name, String text, boolean positive, User user) {
		this.name = name;
		this.text = text;
		this.positive = positive;
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isPositive() {
		return positive;
	}

	public void setPositive(boolean positive) {
		this.positive = positive;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public String getLocalDateAsString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");
		String localDate = date.format(formatter);
		return localDate;
	}
}
