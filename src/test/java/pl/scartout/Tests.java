package pl.scartout;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.is;

import pl.scartout.controller.ProductEditController;
import pl.scartout.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {

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
    public void shouldReturnNetPriceNewProductWithId() {
		
		//given
		Product product = new Product(9999999L, null, null, null,null, 2300.00, 23.00, null, null, null, null, null);
	
		//when
		double priceNet = product.getPriceNet();
		
		//then
		assertThat(priceNet, is(1869.92));
	}
	
    @Test
    public void shouldReturnNetPriceEditProduct() {

        // given
    	double price = 2300.00;
    	double vat = 23.00;

        // when
    	ProductEditController productEditController = new ProductEditController();
        double priceNet = productEditController.netCounter(price, vat);

        // then
        assertThat(priceNet, is(1869.92));
    }

}
