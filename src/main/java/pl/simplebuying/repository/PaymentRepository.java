package pl.simplebuying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.simplebuying.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
