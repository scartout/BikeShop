package pl.scartout.repo;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.runners.MethodSorters;

import pl.scartout.model.Category;
import pl.scartout.model.Manufacturer;
import pl.scartout.model.Product;
import pl.scartout.repo.CategoryRepo;
import pl.scartout.repo.ManufacturerRepo;
import pl.scartout.repo.ProductRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductRepoTest {

	@Autowired
	ProductRepo productRepo;
	@Autowired
	ManufacturerRepo manufacturerRepo;
	@Autowired
	CategoryRepo categoryRepo;

	static Product productNull;
	static Product product1;
	static Product product2;
	static Product product3;
	static Category category1;
	static Category category2;
	static Manufacturer producer1;
	
	@BeforeClass
	public static void before() {
		category1 = new Category(1L, "Road");
		category2 = new Category(2L, "MTB");
		producer1 = new Manufacturer(1L, "Canyon");
		productNull = new Product();
		product1 = new Product(1L, "SKU-TEST-999999999", "Canyon Endurace 7.0 Al", "Shimano 105 Aluminium Bike", "Canyon Endurace 7.0 Al", 
    			"S, M, L, XL, XLL",	4999.00, 23.00, "http://cdn.mos.bikeradar.imdserve.com/images/bikes-and-gear/bikes/road/1450354270219-ob3hv3e725rn-630-80.jpg", 
    			"https://i.pinimg.com/736x/11/4e/11/114e115a0a8005449d1ea616dbfc19f5--search-wheels.jpg", 
    			"http://keyassets.timeincuk.net/inspirewp/live/wp-content/uploads/sites/2/2017/06/Canyon-Endurace-AL-7.0.jpg", category1, producer1);
		product2 = new Product(2L, "SKU-TEST-999999999", "Rose Pro SL 105", "Shimano 105 Aluminium Bike", "Rose Pro SL 105", 
    			"S, M, L, XL, XLL",	4899.00, 23.00, "https://media1.rosebikes.de/drehmomente/2147244_6p5l46zavw_800_580/data/spinpict/004.jpg", 
    			"http://road.cc/sites/default/files/styles/main_width/public/rose-pro-sl-105.jpg?itok=atfFenQP", 
    			"https://media1.rosebikes.de/drehmomente/2147837_gmwy8kteux_800_580/data/spinpict/004.jpg", category1, producer1);
		product3 = new Product(3L, "SKU-TEST-999999999", "Unibike Viper", "Shimano Alivio Aluminium Bike", "Unibike Viper", 
    			"S, M, L, XL, XLL",	2499.00, 23.00, "http://www.bikekatalog.pl/2012/ppg_fotki/foto_max/8_81733_28_02_12.jpg", 
    			"http://st2.static.bikestats.pl/55/b5655-u7699_orig.jpg?1387525943", 
    			"http://www.unibike.pl/images/cross/vipergts7.jpg", category2, producer1);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void t1shouldNotCreateWithValidation() {
		productRepo.save(productNull);
	}
	
	@Test
	public void t2shouldSave() {
		categoryRepo.save(category1);
		categoryRepo.save(category2);
		manufacturerRepo.save(producer1);
    	productRepo.save(product1);
    	productRepo.save(product2);
    	productRepo.save(product3);
	}
	
	@Test
	public void t3shouldFindById() {
		Product product = productRepo.findById(1L);
		assertThat(product.getId()).isEqualTo(1L);
		assertThat(product.getName()).isEqualTo("Canyon Endurace 7.0 Al");
		assertThat(product.getDescriptionShort()).isEqualTo("Shimano 105 Aluminium Bike");
	}
	
	@Test
	public void t4shouldFindByName() {
		Product product = productRepo.findByName("Canyon Endurace 7.0 Al");
		assertThat(product.getId()).isEqualTo(1L);
		assertThat(product.getName()).isEqualTo("Canyon Endurace 7.0 Al");
		assertThat(product.getDescriptionShort()).isEqualTo("Shimano 105 Aluminium Bike");
	}
	
	@Test
	public void t5shouldfindAllByCategoryId() {
    	List<Product> products = productRepo.findAllByCategoryId(1L);
    	assertThat(products).hasSize(2);
	}
	
	@Test
	public void t6shouldfindAllByString() {
    	List<Product> products = productRepo.searchProducts("Shimano 105 Aluminium Bike");
    	assertThat(products).hasSize(2);
	}
	
	@Test
	public void t7shouldfindAllByString2() {
    	List<Product> products = productRepo.searchProducts("Shimano");
    	assertThat(products).hasSize(3);
	}
	
	@Test
	public void t8shouldfindAllByString3() {
    	List<Product> products = productRepo.searchProducts("Specialized");
    	assertThat(products).isEmpty();
	}
	
	@Test
	public void t8update() {
    	productRepo.updateProduct(1L, "SKU-TEST-999999999", "Canyon Endurace 7.0 Al", "Shimano 105 Aluminium Bike", "Canyon Endurace 7.0 Al", 
    	"S, M, L, XL, XLL",	4899.00, 23.00, 3982.93, "http://cdn.mos.bikeradar.imdserve.com/images/bikes-and-gear/bikes/road/1450354270219-ob3hv3e725rn-630-80.jpg", 
    	"https://i.pinimg.com/736x/11/4e/11/114e115a0a8005449d1ea616dbfc19f5--search-wheels.jpg", 
    	"http://keyassets.timeincuk.net/inspirewp/live/wp-content/uploads/sites/2/2017/06/Canyon-Endurace-AL-7.0.jpg", category1, producer1);
    	Product product = productRepo.findById(1L);
		assertThat(product.getId()).isEqualTo(1L);
		assertThat(product.getPrice()).isEqualTo(4899.00);
	}

}
