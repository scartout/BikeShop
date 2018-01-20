package pl.scartout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.Category;
import pl.scartout.model.Producer;
import pl.scartout.repo.CategoryRepo;
import pl.scartout.repo.ProducerRepo;
	 
@Controller
public class AdminController {
    
	private CategoryRepo categoryRepo;
	private ProducerRepo producerRepo;
    
    @Autowired
    public AdminController(CategoryRepo categoryRepo, ProducerRepo producerRepo) {
        this.categoryRepo = categoryRepo;
        this.producerRepo= producerRepo;
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
	
	@PostMapping("/addProducer")
	public String addProducer(@RequestParam String name) {
		Producer producer = new Producer(name);
		producerRepo.save(producer);
		return "redirect:/admin";
	}
	
}