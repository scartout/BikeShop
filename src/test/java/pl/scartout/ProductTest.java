package pl.scartout;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

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
    public void shouldReturnNetPriceNewProduct() {
		
		//given
		Product product = new Product(null, null, null,	null, 2300.00, 23.00, null, null, null);
	
		//when
		double priceNet = product.getPriceNet();
		
		//then
		assertThat(priceNet, is(1869.92));
	}
	
	@Test
    public void shouldReturnNetPriceNewProductWithoutVat() {
		
		//given
		Product product = new Product(null, null, null,	null, 2300.00, 0.00, null, null, null);
	
		//when
		double priceNet = product.getPriceNet();
		
		//then
		assertThat(priceNet, is(2300.00));
	}
	
	@Test
    public void shouldReturnNetPriceNewProductWithUpperLimit() {
		
		//given
		Product product = new Product(null, null, null,	null, 2300.00, 99.99, null, null, null);
	
		//when
		double priceNet = product.getPriceNet();
		
		//then
		assertThat(priceNet, is(1150.06));
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void shouldReturnNetPriceNewProductWithIncorrectVatValue() {
		
		Product product = new Product(null, null, null,	null, 2300.00, 100.01, null, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void shouldReturnNetPriceNewProductWithNegativeVatValue() {
		
		Product product = new Product(null, null, null,	null, 2300.00, -0.01, null, null, null);
	}
	
	@Test
    public void shouldReturnNetPriceNewProductWithId() {
		
		//given
		Product product = new Product(9999999L, null, null, null,null, 2300.00, 23.00, null, null, null, null, null);
	
		//when
		double priceNet = product.getPriceNet();
		
		//then
		assertThat(priceNet, is(1869.92));
	}
	
	@Test
    public void shouldReturnNetPriceNewProductWithIdWithoutVat() {
		
		//given
		Product product = new Product(9999999L, null, null, null,null, 2300.00, 0.00, null, null, null, null, null);
	
		//when
		double priceNet = product.getPriceNet();
		
		//then
		assertThat(priceNet, is(2300.00));
	}
	
	@Test
    public void shouldReturnNetPriceNewProductWithIdUpperLimit() {
		
		//given
		Product product = new Product(9999999L, null, null, null,null, 2300.00, 99.99, null, null, null, null, null);
	
		//when
		double priceNet = product.getPriceNet();
		
		//then
		assertThat(priceNet, is(1150.06));
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void shouldReturnNetPriceNewProductWithIdWithIncorrectVatValue() {
		
		Product product = new Product(9999999L, null, null, null,null, 2300.00, 100.01, null, null, null, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void shouldReturnNetPriceNewProductWithIdWithNegativeVatValue() {
		
		Product product = new Product(9999999L, null, null, null,null, 2300.00, -0.01, null, null, null, null, null);
	}
	
}
