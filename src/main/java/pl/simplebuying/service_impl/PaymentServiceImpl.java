package pl.simplebuying.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Payment;
import pl.simplebuying.repository.PaymentRepository;
import pl.simplebuying.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	PaymentRepository paymentRepository;

	@Autowired
	public PaymentServiceImpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}
	
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}
}
