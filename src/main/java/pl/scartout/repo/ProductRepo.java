package pl.scartout.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Category;
import pl.scartout.model.Producer;
import pl.scartout.model.Product;

@Transactional
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	List<Product> findAllByCategoryId(Long categoryId);

	Product findById(Long id);

	List<Product> findAllByPriceBetween(double priceMin, double priceMax);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Product c SET c.name=:name, c.descriptionShort=:descriptionShort, c.descriptionLong=:descriptionLong, "
    		+ "c.descriptionSize=:descriptionSize, c.price=:price, c.vat=:vat, c.priceNet=:priceNet,"
    		+ "c.mainImage=:mainImage, c.imageSecond=:imageSecond,"
    		+ "c.imageThird=:imageThird, c.category=:category, c.producer=:producer WHERE c.id = :id")
    int updateProduct(@Param("id") Long id, @Param("name") String name, @Param("descriptionShort") String descriptionShort, 
    		@Param("descriptionLong") String descriptionLong, @Param("descriptionSize") String descriptionSize, 
    		@Param("price") double price, @Param("vat") double vat, @Param("priceNet") double priceNet, @Param("mainImage") String mainImage, 
    		@Param("imageSecond") String imageSecond, @Param("imageThird") String imageThird,
    		@Param("category") Category category, @Param("producer") Producer producer);
	
	@Query("SELECT count(c) FROM Product c")
	Long countAllProducts();
	
	@Query("SELECT count(c) FROM Product c WHERE c.category=:category")
	Long countAllProductsByCategory(@Param("category") Category category);
	
	@Query("SELECT c FROM Product c WHERE c.name like "+"%"+":string"+"% OR c.descriptionShort like "+"%"+":string"+"%")
	List<Product> searchProducts(@Param("string") String string);

}