package pl.scartout.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String getProduct(Model model, HttpServletRequest request) {
    	List<Product> products = productRepo.findAllTheNewest();
    	model.addAttribute("products", products);
    	orderCounter(request);
        return "home";
    }
    
	private void orderCounter(HttpServletRequest request) {
		int countOrders = 0;
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	String username = userDetails.getUsername();
	    	User user = userRepo.findByUsername(username);
	    	countOrders = orderRepo.countOrdersByUserId(user);
		}catch (ClassCastException e){}
		HttpSession session = request.getSession();
		session.setAttribute("countOrders", countOrders);
	}
    
}