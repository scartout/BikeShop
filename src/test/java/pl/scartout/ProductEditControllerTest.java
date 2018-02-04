package pl.scartout;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.is;

import pl.scartout.controller.ProductEditController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEditControllerTest {
	
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
    
    @Test
    public void shouldReturnNetPriceEditProductWithoutVat() {

        // given
    	double price = 2300.00;
    	double vat = 0.00;

        // when
    	ProductEditController productEditController = new ProductEditController();
        double priceNet = productEditController.netCounter(price, vat);

        // then
        assertThat(priceNet, is(2300.00));
    }
    
    @Test
    public void shouldReturnNetPriceEditProductUpperLimit() {

        // given
    	double price = 2300.00;
    	double vat = 99.99;

        // when
    	ProductEditController productEditController = new ProductEditController();
        double priceNet = productEditController.netCounter(price, vat);

        // then
        assertThat(priceNet, is(1150.06));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnNetPriceEditProductWithIncorrectVat() {

        // given
    	double price = 2300.00;
    	double vat = 100.01;

        // when
    	ProductEditController productEditController = new ProductEditController();
        productEditController.netCounter(price, vat);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnNetPriceEditProductWithNegativeVat() {

        // given
    	double price = 2300.00;
    	double vat = -0.01;

        // when
    	ProductEditController productEditController = new ProductEditController();
        productEditController.netCounter(price, vat);

    }

}
