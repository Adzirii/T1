package exercise.adziri.t1.repositories;

import exercise.adziri.t1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}