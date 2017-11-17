package pl.simplebuying.domain.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.domain.order.PaymentService;

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
