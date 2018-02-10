package pl.scartout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.scartout.model.Category;
import pl.scartout.model.Producer;
import pl.scartout.model.Product;
import pl.scartout.model.Role;
import pl.scartout.repo.CategoryRepo;
import pl.scartout.repo.ProducerRepo;
import pl.scartout.repo.ProductRepo;
import pl.scartout.repo.RoleRepo;

@SpringBootApplication
public class SpringJpaBootApplication{
	
    public static void main(String[] args) throws InterruptedException{
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringJpaBootApplication.class, args);

        RoleRepo roleRepo = ctx.getBean(RoleRepo.class);
        if (roleRepo.findByRole("admin") == null) {
        	Role role1 = new Role(1L, "admin");
        	roleRepo.save(role1);}
        if (roleRepo.findByRole("user") == null) {
        	Role role2 = new Role(2L, "user");
        	roleRepo.save(role2);}
        
        //DEMO RECORDS
        
        CategoryRepo categoryRepo = ctx.getBean(CategoryRepo.class);
        
        if (categoryRepo.findByName("Road") == null) {
        	Category category1 = new Category(1L, "Road");
        	categoryRepo.save(category1);}

        if (categoryRepo.findByName("MTB") == null) {
        	Category category2 = new Category(2L, "MTB");
        	categoryRepo.save(category2);}

        if (categoryRepo.findByName("Cross/Trekking") == null) {
        	Category category3 = new Category(3L, "Cross/Trekking");
        	categoryRepo.save(category3);}

        if (categoryRepo.findByName("City") == null) {
        	Category category4 = new Category(4L, "City");
        	categoryRepo.save(category4);}
        
        if (categoryRepo.findByName("Accesories") == null) {
        	Category category5 = new Category(5L, "Accesories");
        	categoryRepo.save(category5);}
        
        ProducerRepo producerRepo = ctx.getBean(ProducerRepo.class);
        
        if (producerRepo.findByName("Canyon") == null) {
        	Producer producer1 = new Producer(1L, "Canyon");
        	producerRepo.save(producer1);}
        
        if (producerRepo.findByName("Rose") == null) {
        	Producer producer2 = new Producer(2L, "Rose");
        	producerRepo.save(producer2);}
        
        if (producerRepo.findByName("Unibike") == null) {
        	Producer producer3 = new Producer(3L, "Unibike");
        	producerRepo.save(producer3);}
        
        if (producerRepo.findByName("Crank Brothers") == null) {
        	Producer producer4 = new Producer(4L, "Crank Brothers");
        	producerRepo.save(producer4);}
        
        ProductRepo productRepo = ctx.getBean(ProductRepo.class);
  
        if (productRepo.findByName("Canyon Endurace 7.0 Al") == null) {
        	Producer producer = producerRepo.findByName("Canyon");
        	Category category = categoryRepo.findByName("Road");
        	Product product1 = new Product(1L, "Canyon Endurace 7.0 Al", "Shimano 105 Aluminium Bike", "Canyon Endurace 7.0 Al", 
        			"S, M, L, XL, XLL",	4999.00, 23.00, "http://cdn.mos.bikeradar.imdserve.com/images/bikes-and-gear/bikes/road/1450354270219-ob3hv3e725rn-630-80.jpg", 
        			"https://i.pinimg.com/736x/11/4e/11/114e115a0a8005449d1ea616dbfc19f5--search-wheels.jpg", 
        			"http://keyassets.timeincuk.net/inspirewp/live/wp-content/uploads/sites/2/2017/06/Canyon-Endurace-AL-7.0.jpg", category, producer);
        	productRepo.save(product1);}
        
        if (productRepo.findByName("Rose Pro SL 105") == null) {
        	Producer producer = producerRepo.findByName("Rose");
        	Category category = categoryRepo.findByName("Road");
        	Product product2 = new Product(2L, "Rose Pro SL 105", "Shimano 105 Aluminium Bike", "Rose Pro SL 105", 
        			"S, M, L, XL, XLL",	4899.00, 23.00, "https://media1.rosebikes.de/drehmomente/2147244_6p5l46zavw_800_580/data/spinpict/004.jpg", 
        			"http://road.cc/sites/default/files/styles/main_width/public/rose-pro-sl-105.jpg?itok=atfFenQP", 
        			"https://media1.rosebikes.de/drehmomente/2147837_gmwy8kteux_800_580/data/spinpict/004.jpg", category, producer);
        	productRepo.save(product2);}
        
        if (productRepo.findByName("Unibike Viper") == null) {
        	Producer producer = producerRepo.findByName("Unibike");
        	Category category = categoryRepo.findByName("Cross/Trekking");
        	Product product3 = new Product(3L, "Unibike Viper", "Shimano Alivio Aluminium Bike", "Unibike Viper", 
        			"S, M, L, XL, XLL",	2499.00, 23.00, "http://www.bikekatalog.pl/2012/ppg_fotki/foto_max/8_81733_28_02_12.jpg", 
        			"http://st2.static.bikestats.pl/55/b5655-u7699_orig.jpg?1387525943", 
        			"http://www.unibike.pl/images/cross/vipergts7.jpg", category, producer);
        	productRepo.save(product3);}
    
	    if (productRepo.findByName("Crank Brothers M17") == null) {
	    	Producer producer = producerRepo.findByName("Crank Brothers");
	    	Category category = categoryRepo.findByName("Accesories");
	    	Product product4 = new Product(4L, "Crank Brothers M17", "Bike multitool", "Bike multitool", 
	    			"The m17 combines an aluminum frame that gives superb grip with 17 of the most essential tools a mountain biker will ever need. It features 2-, 2.5-, 3-, 4-, 5-, 6- and 8-millimeter hex wrenches, Philips and flathead screwdrivers and a Torx T25.",	
	    			94.90, 23.00, "https://www.sbike.pl/public/cache/img/resources/private/offer/1/00009687/14759/bhBPPKGBgAZbHZVPPagG/650x650.jpg", 
	    			"http://team29er.pl/images/2016/Multitool_Crank_Brothers/Test_Multitool_Crank_Brothers_M17.jpg", 
	    			"http://road.cc/sites/default/files/images/Crank%20Bros%20M17%20multitool%202.preview_500.jpg", category, producer);
	    	productRepo.save(product4);}
	}
    
    	
}