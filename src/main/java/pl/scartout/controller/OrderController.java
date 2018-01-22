package pl.scartout.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.Order;
import pl.scartout.model.Product;
import pl.scartout.model.User;
import pl.scartout.repo.ProductRepo;
import pl.scartout.repo.UserRepo;
import pl.scartout.repo.OrderRepo;

@Controller
public class OrderController {
	
	private ProductRepo productRepo;
	private UserRepo userRepo;
	private OrderRepo orderRepo;
	 
    @Autowired
    public OrderController(ProductRepo productRepo, UserRepo userRepo, OrderRepo orderRepo) {
        this.productRepo= productRepo;
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
    }
    
    @GetMapping(path = "/cart", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCarts(Model model) {
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = userDetails.getUsername();
    	User user = userRepo.findByUsername(username);
    	List <Order> orders = orderRepo.findByUserAndOrderDateIsNull(user);
    	model.addAttribute("orders", orders);
        return "cart";
    }
    
    @GetMapping(path = "/ordersedit", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOrders(Model model) {
    	List <Order> orders = orderRepo.findByOrderDateIsNotNull();
    	model.addAttribute("orders", orders);
        return "ordersedit";
    }
    
    @GetMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOrdersByUser(Model model) {
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = userDetails.getUsername();
    	User user = userRepo.findByUsername(username);
    	List <Order> orders = orderRepo.findByUserAndOrderDateIsNotNull(user);
    	model.addAttribute("orders", orders);
        return "orders";
    }
    
    @PostMapping(path = "/addtocart", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addToCart(Model model, @RequestParam Long productId, @RequestParam int quantity) {
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = userDetails.getUsername();
    	User user = userRepo.findByUsername(username);
    	Product product = productRepo.findById(productId);
    	Order order = new Order(quantity);
    	order.setProduct(product);
    	order.setUser(user);
    	orderRepo.save(order);
        return "redirect:/product?id="+productId;
    }
    
    @PostMapping("/confirmOrder")
	public String confirmOrder(@RequestParam Long id, @RequestParam int quantity, @RequestParam double price) {
    	double total = price*quantity;
    	orderRepo.confirmOrder(id, new Date(), quantity, total);
		return "redirect:/cart";
	}
    
    @PostMapping("/deleteOrder")
	public String confirmOrder(@RequestParam Long id) {
    	orderRepo.deleteOrder(id);
		return "redirect:/cart";
	}
    
    @PostMapping("/confirmApproval")
	public String confirmApproval(@RequestParam Long id) {
    	orderRepo.confirmApproval(id, new Date());
		return "redirect:/ordersedit";
	}
    
    @PostMapping("/confirmShipping")
	public String confirmShipping(@RequestParam Long id) {
    	orderRepo.confirmShipping(id, new Date());
		return "redirect:/ordersedit";
	}
    
    @PostMapping("/confirmComplete")
	public String confirmComplete(@RequestParam Long id) {
    	orderRepo.confirmComplete(id, new Date());
		return "redirect:/ordersedit";
	}

}