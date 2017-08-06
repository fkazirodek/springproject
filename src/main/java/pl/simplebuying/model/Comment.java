package pl.simplebuying.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "comments")
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
	@JoinColumn(name = "receiver_id", nullable = false)
	private User receiver;
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;

	public Comment() {
		date = LocalDate.now();
	}

	public Comment(String name, String text, boolean positive, User receiver) {
		this.name = name;
		this.text = text;
		this.positive = positive;
		this.receiver = receiver;
	}

	public Long getId() {
		return id;
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

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getLocalDateAsString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");
		String localDate = date.format(formatter);
		return localDate;
	}
}
