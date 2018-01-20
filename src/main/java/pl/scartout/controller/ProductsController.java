package pl.scartout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.Category;
import pl.scartout.model.Product;
import pl.scartout.repo.CategoryRepo;
import pl.scartout.repo.ProductRepo;

@Controller
public class ProductsController {
	
	private ProductRepo productRepo;
	private CategoryRepo categoryRepo;
	 
    @Autowired
    public ProductsController(ProductRepo productRepo, CategoryRepo categoryRepo) {
    	this.productRepo = productRepo;
    	this.categoryRepo = categoryRepo;
    }
    
    @GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProduct(Model model, @RequestParam long id) {
    	List<Product> products = productRepo.findAllByCategoryId(id);
    	model.addAttribute("products", products);
    	Category category = categoryRepo.findById(id);
    	Long countProducts = productRepo.countAllProductsByCategory(category);
    	model.addAttribute("countProducts", countProducts);
        return products;
    }
    
    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProduct2(Model model, @RequestParam double priceMin, @RequestParam double priceMax) {
    	List<Product> products = productRepo.findAllByPriceBetween(priceMin, priceMax);
    	model.addAttribute("products", products);
        return products;
    }

}