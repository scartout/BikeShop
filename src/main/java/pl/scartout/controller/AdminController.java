package pl.scartout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.Category;
import pl.scartout.model.Manufacturer;
import pl.scartout.repo.CategoryRepo;
import pl.scartout.repo.ManufacturerRepo;
	 
@Controller
public class AdminController {
    
	private CategoryRepo categoryRepo;
	private ManufacturerRepo manufacturerRepo;
    
    @Autowired
    public AdminController(CategoryRepo categoryRepo, ManufacturerRepo manufacturerRepo) {
        this.categoryRepo = categoryRepo;
        this.manufacturerRepo= manufacturerRepo;
    }
	
    @RequestMapping("/admin")
    public String home() {
        return "admin";
    }
    
	@PostMapping("/addCategory")
	public String addCategory(@RequestParam String name) {
		Category category = new Category(name);
		categoryRepo.save(category);
		return "redirect:/admin";
	}
	
	@PostMapping("/addManufacturer")
	public String addManufacturer(@RequestParam String name) {
		Manufacturer manufacturer = new Manufacturer(name);
		manufacturerRepo.save(manufacturer);
		return "redirect:/admin";
	}
	
}