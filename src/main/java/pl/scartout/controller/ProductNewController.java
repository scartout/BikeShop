package pl.scartout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.Category;
import pl.scartout.model.Manufacturer;
import pl.scartout.model.Product;
import pl.scartout.repo.CategoryRepo;
import pl.scartout.repo.ManufacturerRepo;
import pl.scartout.repo.ProductRepo;
	 
@Controller
public class ProductNewController {

	private CategoryRepo categoryRepo;
	private ManufacturerRepo manufacturerRepo;
	private ProductRepo productRepo;
    
    @Autowired
    public ProductNewController(CategoryRepo categoryRepo, ManufacturerRepo manufacturerRepo, ProductRepo productRepo) {
        this.categoryRepo = categoryRepo;
        this.manufacturerRepo = manufacturerRepo;
        this.productRepo = productRepo;
    }    
    
    @RequestMapping("/productnew")
    public String home(Model model) {
    	List <Category> categories = categoryRepo.findAll();
    	model.addAttribute("categories", categories);
    	List <Manufacturer> manufacturers = manufacturerRepo.findAll();
    	model.addAttribute("manufacturers", manufacturers);
        return "productnew";
    }
    
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct(@RequestParam String sku, @RequestParam String name, @RequestParam String descriptionShort, @RequestParam String descriptionLong,
			@RequestParam String descriptionSize, @RequestParam double price, @RequestParam double vat,
			@RequestParam String categoryName, @RequestParam String manufacturerName, @RequestParam String mainImage,
			@RequestParam String imageSecond, @RequestParam String imageThird) {
		Product product = new Product(sku, name, descriptionShort, descriptionLong, descriptionSize, price, vat, mainImage, imageSecond, imageThird);
		Category category = categoryRepo.findByName(categoryName);
		Manufacturer manufacturer = manufacturerRepo.findByName(manufacturerName);
		product.setCategory(category);
		product.setManufacturer(manufacturer);
		productRepo.save(product);
		return "redirect:/admin";
	}
	
}