package pl.simplebuying.domain.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface ItemRepository extends JpaRepository<Item, Long> {
	
	List<Item> findBySeller_id(Long id);
	
	@Query("SELECT i FROM Item i WHERE i.itemName LIKE %:name%")
	List<Item> findByItemName(@Param("name") String name);
	
	List<Item> findByCategory_id(Long id);
}
