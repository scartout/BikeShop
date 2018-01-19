package pl.scartout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.Comment;
import pl.scartout.model.Image;
import pl.scartout.model.Product;
import pl.scartout.repo.CommentRepo;
import pl.scartout.repo.ImageRepo;
import pl.scartout.repo.ProductRepo;

@Controller
public class ProductController {
	
	private ProductRepo productRepo;
	private ImageRepo imageRepo;
	private CommentRepo commentRepo;
	 
    @Autowired
    public ProductController(ProductRepo productRepo, ImageRepo imageRepo, CommentRepo commentRepo) {
    	this.productRepo = productRepo;
    	this.imageRepo = imageRepo;
    	this.commentRepo = commentRepo;
    }
    
    @GetMapping(path = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProduct(Model model, @RequestParam long id) {
    	Product product = productRepo.findById(id);
    	model.addAttribute("product", product);
    	List <Image> images = imageRepo.findByProductId(id);
    	model.addAttribute("images", images);
    	List <Comment> comments = commentRepo.findByProductIdOrderByDateDesc(id);
    	model.addAttribute("comments", comments);
    	long countComments = commentRepo.countCommentsByProduct(product);
    	model.addAttribute("countComments", countComments);
        return "product";
    }

}