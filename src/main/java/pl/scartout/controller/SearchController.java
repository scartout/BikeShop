package pl.scartout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.scartout.model.Product;
import pl.scartout.repo.ProductRepo;
 
@Controller
public class SearchController {
	
	private ProductRepo productRepo;
	
    @Autowired
    public SearchController(ProductRepo productRepo) {
    	this.productRepo= productRepo;
    }
    
    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProduct(Model model, @RequestParam(required=false) String string) {
    	List<Product> products;
    	if (string != null) {
    		products = productRepo.searchProducts(string);
    	}
    	else products = productRepo.findAll();
    	model.addAttribute("products", products);
        return "search";
    }
    
}