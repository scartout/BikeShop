package pl.scartout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.Category;
import pl.scartout.model.Producer;
import pl.scartout.model.Product;
import pl.scartout.repo.CategoryRepo;
import pl.scartout.repo.ProducerRepo;
import pl.scartout.repo.ProductRepo;

@Controller
public class ProductEditController {
	
	private CategoryRepo categoryRepo;
	private ProducerRepo producerRepo;
	private ProductRepo productRepo;
	 
    @Autowired
    public ProductEditController(CategoryRepo categoryRepo, ProducerRepo producerRepo, ProductRepo productRepo) {
        this.categoryRepo = categoryRepo;
        this.producerRepo= producerRepo;
        this.productRepo= productRepo;
    }
    
    @GetMapping(path = "/productlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProducts(Model model) {
    	List<Product> products = productRepo.findAll();
    	model.addAttribute("products", products);
        return "productlist";
    }
    
    @GetMapping(path = "/productedit", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProduct(Model model, @RequestParam Long id) {
    	Product product = productRepo.findById(id);
    	model.addAttribute("product", product);
    	List <Category> categories = categoryRepo.findAll();
    	model.addAttribute("categories", categories);
    	List <Producer> producers = producerRepo.findAll();
    	model.addAttribute("producers", producers);
		return "productedit";
    }
    
    @PostMapping("/updateProduct")
	public String addProduct(@RequestParam Long id, @RequestParam String name, @RequestParam String descriptionShort, @RequestParam String descriptionLong,
			@RequestParam String descriptionSize, @RequestParam double price, @RequestParam double vat,
			@RequestParam String categoryName, @RequestParam String producerName, @RequestParam String mainImage,
			@RequestParam String imageSecond, @RequestParam String imageThird) {
		Category category = categoryRepo.findByName(categoryName);
		Producer producer = producerRepo.findByName(producerName);
		double priceNet = price*(1-vat/100);
    	productRepo.updateProduct(id, name, descriptionShort, descriptionLong, descriptionSize, price, vat, priceNet, mainImage, imageSecond, imageThird, category, producer);
		return "redirect:/admin";
	}

}