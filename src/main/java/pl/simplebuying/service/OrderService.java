package pl.simplebuying.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import pl.simplebuying.model.Address;

@Service
public class OrderService {

	public boolean checkUserAddress(Address address) {
		return address == null? false : true;
	}
	
	public String getDateAsString() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");
		String formattedDate = date.format(formatter);
		return formattedDate;
	}
	
}
