package pl.scartout.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.User;
import pl.scartout.service.UserService;
	 
@Controller
public class UserController {
	
	private UserService userService;
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/register")
    public String home(Model model) {
    	model.addAttribute("user", new User());
        return "registerForm";
    }
    
    @GetMapping("/login")
    public String homeLogin(Model model) {
    	model.addAttribute("user", new User());
        return "login";
    }
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute @Valid User user,
			BindingResult bindResult) {
		if(bindResult.hasErrors())
			return "registerForm";
		else {
			userService.addWithDefaultRole(user);
			return "registerSuccess";
		}
	}
	
}