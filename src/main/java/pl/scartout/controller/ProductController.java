package pl.scartout.controller;

import java.util.List;

import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.Comment;
import pl.scartout.model.Product;
import pl.scartout.repo.CommentRepo;
import pl.scartout.repo.ProductRepo;

@Controller
public class ProductController {
	
	private ProductRepo productRepo;
	private CommentRepo commentRepo;
	 
    @Autowired
    public ProductController(ProductRepo productRepo, CommentRepo commentRepo) {
    	this.productRepo = productRepo;
    	this.commentRepo = commentRepo;
    }
    
    @GetMapping(path = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProduct(Model model, @RequestParam long id) {
    	Product product = productRepo.findById(id);
    	model.addAttribute("product", product);
    	List <Comment> comments = commentRepo.findByProductIdOrderByDateDesc(id);
    	model.addAttribute("comments", comments);
    	long countComments = commentRepo.countCommentsByProduct(product);
    	model.addAttribute("countComments", countComments);
    	try{
    		double avgComments = commentRepo.avgCommentsByProduct(product);
    		model.addAttribute("avgComments", avgComments);
    	}catch(AopInvocationException e) {
    		model.addAttribute("avgComments", 0);
    	}
        return "product";
    }

}