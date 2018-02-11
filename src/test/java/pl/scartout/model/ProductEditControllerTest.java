package pl.scartout.model;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import pl.scartout.controller.ProductEditController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEditControllerTest {
	
    @Test
    public void shouldReturnNetPrice() {

        // given
    	double price = 2300.00;
    	double vat = 23.00;

        // when
    	ProductEditController productEditController = new ProductEditController();
        double priceNet = productEditController.netCounter(price, vat);

        // then
        assertThat(priceNet).isEqualTo(1869.92);
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
        assertThat(priceNet).isEqualTo(2300.00);
    }
    
    @Test
    public void shouldReturnNetPriceWithUpperLimit() {

        // given
    	double price = 2300.00;
    	double vat = 99.99;

        // when
    	ProductEditController productEditController = new ProductEditController();
        double priceNet = productEditController.netCounter(price, vat);

        // then
        assertThat(priceNet).isEqualTo(1150.06);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNetPriceWithIncorrectVat() {

        // given
    	double price = 2300.00;
    	double vat = 100.01;

        // when
    	ProductEditController productEditController = new ProductEditController();
        productEditController.netCounter(price, vat);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNetPriceWithNegativePriceAndIncorrectVat() {

        // given
    	double price = -2300.00;
    	double vat = 100.01;

        // when
    	ProductEditController productEditController = new ProductEditController();
        productEditController.netCounter(price, vat);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNetPriceWithNegativeVat() {

        // given
    	double price = 2300.00;
    	double vat = -0.01;

        // when
    	ProductEditController productEditController = new ProductEditController();
        productEditController.netCounter(price, vat);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNetPriceWithNegativePrice() {

        // given
    	double price = -2300.00;
    	double vat = 23.00;

        // when
    	ProductEditController productEditController = new ProductEditController();
        productEditController.netCounter(price, vat);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNetPriceWithNegaticePriceAndNegativeVat() {

        // given
    	double price = -2300.00;
    	double vat = -0.01;

        // when
    	ProductEditController productEditController = new ProductEditController();
        productEditController.netCounter(price, vat);

    }

}
