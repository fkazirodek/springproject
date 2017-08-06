package pl.simplebuying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Payment;
import pl.simplebuying.repository.PaymentRepository;

@Service
public class PaymentService {

	PaymentRepository paymentRepository;

	@Autowired
	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}
	
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}
}
