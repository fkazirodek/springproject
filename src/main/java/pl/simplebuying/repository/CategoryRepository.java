package pl.simplebuying.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.simplebuying.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
