package pl.scartout.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.scartout.model.Comment;
import pl.scartout.model.Product;
import pl.scartout.model.User;
import pl.scartout.repo.CommentRepo;
import pl.scartout.repo.ProductRepo;
import pl.scartout.repo.UserRepo;
 
@Controller
public class CommentController {

	private CommentRepo commentRepo;
	private UserRepo userRepo;
	private ProductRepo productRepo;
	 
    @Autowired
    public CommentController(CommentRepo commentRepo, UserRepo userRepo, ProductRepo productRepo) {
    	this.commentRepo = commentRepo;
    	this.userRepo = userRepo;
    	this.productRepo = productRepo;
    }
    
    @PostMapping(path = "/addcomment", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addComment(Model model, @RequestParam String description, @RequestParam long productId, @RequestParam double vote) {
    	Comment comment = new Comment(description, new Date(), vote);
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = userDetails.getUsername();
    	User user = userRepo.findByUsername(username);
    	Product product = productRepo.findById(productId);
    	comment.setProduct(product);
    	comment.setUser(user);
    	commentRepo.save(comment);
        return "redirect:/product?id="+productId;
    }

}