package pl.scartout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.scartout.model.Product;
import pl.scartout.repo.ProductRepo;
 
@Controller
public class HomeController {
	
	private ProductRepo productRepo;
	
    @Autowired
    public HomeController(ProductRepo productRepo) {
    	this.productRepo= productRepo;
    }
    
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProduct(Model model) {
    	List<Product> products = productRepo.findAll();
    	model.addAttribute("products", products);
    	Long countProducts = productRepo.countAllProducts();
    	model.addAttribute("countProducts", countProducts);
        return "home";
    }
    
}