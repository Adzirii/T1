package exercise.adziri.t1.repository;

import exercise.adziri.t1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}