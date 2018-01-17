package pl.scartout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.scartout.model.Product;
import pl.scartout.repo.CategoryRepo;
import pl.scartout.repo.ProductRepo;
 
@Controller
public class ProductController {

	private ProductRepo productRepo;
	private CategoryRepo categoryRepo;
	
    @Autowired
    public ProductController(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo= categoryRepo;
    }
	
    @RequestMapping("/products")
    public String home() {
        return "products";
    }
    
    @RequestMapping("/product")
    public String homeProduct() {
        return "product";
    }
	
    @GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProduct(Model model, @RequestParam String category) {
    	
    	Long categoryId = categoryRepo.idByName(category);
    	List<Product> products = productRepo.findAllByCategoryId(categoryId);
    	if (category == null) products = productRepo.findAll();
    	model.addAttribute("products", products);
        return products;
    }
    


}