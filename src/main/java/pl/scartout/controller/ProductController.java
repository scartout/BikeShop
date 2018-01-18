package pl.scartout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.Product;
import pl.scartout.repo.ProductRepo;

@Controller
public class ProductController {
	
	private ProductRepo productRepo;
	 
    @Autowired
    public ProductController(ProductRepo productRepo) {
    	this.productRepo= productRepo;
    }
    
    @GetMapping(path = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProduct(Model model, @RequestParam long id) {
    	Product product = productRepo.findById(id);
    	model.addAttribute("product", product);
        return product;
    }

}