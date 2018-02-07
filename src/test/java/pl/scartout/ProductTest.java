package pl.scartout;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import pl.scartout.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

	@Test
	public void create() {
		Product product = new Product();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Product.class).verify();
	}
	
	@Test
	public void verifyToString() {
		Product product = new Product();
		product.setId(9999999L);
		product.setName("Product_name");
		assertThat(product.toString()).isEqualTo("Product - 9999999 - Product_name");
	}
	
	@Test
    public void shouldReturnNetPrice() {
		
		//given
		Product product = new Product(null, null, null,	null, 2300.00, 23.00, null, null, null);
	
		//when
		double priceNet = product.getPriceNet();
		
		//then
		assertThat(priceNet, is(1869.92));
	}
	
	@Test
    public void shouldReturnNetPriceWithoutVat() {
		
		//given
		Product product = new Product(null, null, null,	null, 2300.00, 0.00, null, null, null);
	
		//when
		double priceNet = product.getPriceNet();
		
		//then
		assertThat(priceNet, is(2300.00));
	}
	
	@Test
    public void shouldReturnNetPriceWithUpperLimit() {
		
		//given
		Product product = new Product(null, null, null,	null, 2300.00, 99.99, null, null, null);
	
		//when
		double priceNet = product.getPriceNet();
		
		//then
		assertThat(priceNet, is(1150.06));
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNetPriceWithIncorrectVat() {
		
		Product product = new Product(null, null, null,	null, 2300.00, 100.01, null, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNetPricetWithNegativeVat() {
		
		Product product = new Product(null, null, null,	null, 2300.00, -0.01, null, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNetPriceWithNegativePrice() {
		
		Product product = new Product(null, null, null,null, -2300.00, 23.00, null, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNetPriceWithNegativePriceAndNegativeVat() {
		
		Product product = new Product(null, null, null,null, -2300.00, -23.00, null, null, null);
	}
	
	@Test
    public void shouldReturnNetPriceWithId() {
		
		//given
		Product product = new Product(9999999L, null, null, null,null, 2300.00, 23.00, null, null, null, null, null);
	
		//when
		double priceNet = product.getPriceNet();
		
		//then
		assertThat(priceNet, is(1869.92));
	}
	
	@Test
    public void shouldReturnNetPriceWithIdWithoutVat() {
		
		//given
		Product product = new Product(9999999L, null, null, null,null, 2300.00, 0.00, null, null, null, null, null);
	
		//when
		double priceNet = product.getPriceNet();
		
		//then
		assertThat(priceNet, is(2300.00));
	}
	
	@Test
    public void shouldReturnNetPriceWithIdUpperLimit() {
		
		//given
		Product product = new Product(9999999L, null, null, null,null, 2300.00, 99.99, null, null, null, null, null);
	
		//when
		double priceNet = product.getPriceNet();
		
		//then
		assertThat(priceNet, is(1150.06));
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNetPriceWithIdAndIncorrectVat() {
		
		Product product = new Product(9999999L, null, null, null, null, 2300.00, 100.01, null, null, null, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void shouldReturnNetPriceWithIdAndNegativeVat() {
		
		Product product = new Product(9999999L, null, null, null, null, 2300.00, -0.01, null, null, null, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNetPriceWithIdAndNegativePrice() {
		
		Product product = new Product(9999999L, null, null, null, null, -2300.00, 23.00, null, null, null, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNetPriceWithIdAndNegativePriceValueAndNegativeVatValue() {
		
		Product product = new Product(9999999L, null, null, null, null, -2300.00, -23.00, null, null, null, null, null);
	}
	
}
