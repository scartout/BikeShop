package pl.scartout.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.scartout.model.Product;
import pl.scartout.model.User;
import pl.scartout.repo.OrderRepo;
import pl.scartout.repo.ProductRepo;
import pl.scartout.repo.UserRepo;
 
@Controller
public class HomeController {
	
	Logger LOGGER = Logger.getAnonymousLogger();
	
	private ProductRepo productRepo;
	private UserRepo userRepo;
	private OrderRepo orderRepo;
	
    @Autowired
    public HomeController(ProductRepo productRepo, UserRepo userRepo, OrderRepo orderRepo) {
    	this.productRepo= productRepo;
    	this.userRepo = userRepo;
    	this.orderRepo = orderRepo;
    }
    
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProduct(Model model) {
    	List<Product> products = productRepo.findAll();
    	model.addAttribute("products", products);
    	Long countProducts = productRepo.countAllProducts();
    	model.addAttribute("countProducts", countProducts);
    	int countOrders = 0;
    	try {
	    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	String username = userDetails.getUsername();
	    	User user = userRepo.findByUsername(username);
	    	countOrders = orderRepo.countOrdersByUserId(user);
    	}catch (ClassCastException e){
    		LOGGER.log( Level.SEVERE, e.toString(), e );
    	}
    	model.addAttribute("countOrders", countOrders);
        return "home";
    }
    
}