package pl.simplebuying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.simplebuying.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
