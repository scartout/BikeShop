package pl.scartout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.User;
import pl.scartout.repo.UserRepo;
	 
@Controller
public class SettingsController {

	private UserRepo userRepo;
    
    @Autowired
    public void setUserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    
    @GetMapping(path = "/settings", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCarts(Model model) {
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = userDetails.getUsername();
    	User user = userRepo.findByUsername(username);
    	model.addAttribute("user", user);
        return "settings";
    }
	
}