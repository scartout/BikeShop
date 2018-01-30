package pl.scartout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductNewController {

	private CategoryRepo categoryRepo;
	private ProducerRepo producerRepo;
	private ProductRepo productRepo;
    
    @Autowired
    public ProductNewController(CategoryRepo categoryRepo, ProducerRepo producerRepo, ProductRepo productRepo) {
        this.categoryRepo = categoryRepo;
        this.producerRepo = producerRepo;
        this.productRepo = productRepo;
    }    
    
    @RequestMapping("/productnew")
    public String home(Model model) {
    	List <Category> categories = categoryRepo.findAll();
    	model.addAttribute("categories", categories);
    	List <Producer> producers = producerRepo.findAll();
    	model.addAttribute("producers", producers);
        return "productnew";
    }
    
	@PostMapping("/addProduct")
	public String addProduct(@RequestParam String name, @RequestParam String descriptionShort, @RequestParam String descriptionLong,
			@RequestParam String descriptionSize, @RequestParam double price, @RequestParam double vat,
			@RequestParam String categoryName, @RequestParam String producerName, @RequestParam String mainImage,
			@RequestParam String imageSecond, @RequestParam String imageThird) {
		Product product = new Product(name, descriptionShort, descriptionLong, descriptionSize, price, vat, mainImage, imageSecond, imageThird);
		Category category = categoryRepo.findByName(categoryName);
		Producer producer = producerRepo.findByName(producerName);
		product.setCategory(category);
		product.setProducer(producer);
		productRepo.save(product);
		return "redirect:/admin";
	}
	
}