package pl.scartout.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Category;
import pl.scartout.model.Manufacturer;
import pl.scartout.model.Product;

@Transactional
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	Product findById(Long id);
	
	Product findByName(String string);
	
	List<Product> findAllByCategoryId(Long categoryId);
	
	@Query("SELECT c FROM Product c ORDER BY dateAdded DESC")
	List<Product> findAllTheNewest();
	
	@Query("SELECT c FROM Product c WHERE c.name like "+"%"+":string"+"% OR c.descriptionShort like "+"%"+":string"+"%")
	List<Product> searchProducts(@Param("string") String string);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Product c SET c.name=:name, c.sku=:sku, c.descriptionShort=:descriptionShort, c.descriptionLong=:descriptionLong, "
    		+ "c.descriptionSize=:descriptionSize, c.price=:price, c.vat=:vat, c.priceNet=:priceNet,"
    		+ "c.mainImage=:mainImage, c.imageSecond=:imageSecond,"
    		+ "c.imageThird=:imageThird, c.category=:category, c.manufacturer=:manufacturer WHERE c.id = :id")
    int updateProduct(@Param("id") Long id, @Param("sku") String sku, @Param("name") String name, @Param("descriptionShort") String descriptionShort, 
    		@Param("descriptionLong") String descriptionLong, @Param("descriptionSize") String descriptionSize, 
    		@Param("price") double price, @Param("vat") double vat, @Param("priceNet") double priceNet, @Param("mainImage") String mainImage, 
    		@Param("imageSecond") String imageSecond, @Param("imageThird") String imageThird,
    		@Param("category") Category category, @Param("manufacturer") Manufacturer manufacturer);

}