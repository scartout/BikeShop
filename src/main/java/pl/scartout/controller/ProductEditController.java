package pl.scartout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Preconditions;

import pl.scartout.model.Category;
import pl.scartout.model.Manufacturer;
import pl.scartout.model.Product;
import pl.scartout.repo.CategoryRepo;
import pl.scartout.repo.ManufacturerRepo;
import pl.scartout.repo.ProductRepo;

@Controller
public class ProductEditController {
	
	private CategoryRepo categoryRepo;
	private ManufacturerRepo manufacturerRepo;
	private ProductRepo productRepo;
	 
    @Autowired
    public ProductEditController(CategoryRepo categoryRepo, ManufacturerRepo manufacturerRepo, ProductRepo productRepo) {
        this.categoryRepo = categoryRepo;
        this.manufacturerRepo = manufacturerRepo;
        this.productRepo = productRepo;
    }
    
    public ProductEditController() {}

	public double netCounter(double price, double vat) {
		Preconditions.checkArgument(price>=0, "Price cannot be negative");
		Preconditions.checkArgument(vat>=0, "Price cannot be negative");
		Preconditions.checkArgument(vat<=100, "Price cannot be greater than 99.99");
		double priceNet = price/(1+(vat/100.0));
		Preconditions.checkState(priceNet>0, "Price net cannot be negative");
		Preconditions.checkState(priceNet<=price, "Price net cannot be greater than price gross");
		return Math.round(priceNet*100.0)/100.0;
		
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
    	List <Manufacturer> manufacturers = manufacturerRepo.findAll();
    	model.addAttribute("manufacturers", manufacturers);
		return "productedit";
    }
    
    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public String addProduct(@RequestParam Long id, @RequestParam String sku, @RequestParam String name, @RequestParam String descriptionShort, @RequestParam String descriptionLong,
			@RequestParam String descriptionSize, @RequestParam double price, @RequestParam double vat,
			@RequestParam String categoryName, @RequestParam String manufacturerName, @RequestParam String mainImage,
			@RequestParam String imageSecond, @RequestParam String imageThird) {
		Category category = categoryRepo.findByName(categoryName);
		Manufacturer manufacturer = manufacturerRepo.findByName(manufacturerName);
		productRepo.updateProduct(id, sku, name, descriptionShort, descriptionLong, descriptionSize, price, vat, netCounter(price, vat), mainImage, imageSecond, imageThird, category, manufacturer);
		return "redirect:/admin";
	}

}