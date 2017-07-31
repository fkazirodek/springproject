package pl.simplebuying.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.simplebuying.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByUser_id(Long id);

}
