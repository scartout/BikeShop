package pl.scartout.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Product;

@Transactional
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	List<Product> findAllByCategoryId(Long categoryId);

	Product findById(Long id);

	List<Product> findAllByPriceBetween(double valueDown, double valueUp);
}