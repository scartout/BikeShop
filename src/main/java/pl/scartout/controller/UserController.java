package pl.scartout.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.Address;
import pl.scartout.model.Contact;
import pl.scartout.model.User;
import pl.scartout.repo.AddressRepo;
import pl.scartout.repo.ContactRepo;
import pl.scartout.service.UserService;
	 
@Controller
public class UserController {
	
	private UserService userService;
	private AddressRepo addressRepo;
	private ContactRepo contactRepo;
    
    @Autowired
    public void setUserService(UserService userService, AddressRepo addressRepo, ContactRepo contactRepo) {
        this.userService = userService;
        this.addressRepo = addressRepo;
        this.contactRepo = contactRepo;
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
			BindingResult bindResult,
			@RequestParam String city,
			@RequestParam String voivodeship,
			@RequestParam String county,
			@RequestParam String country,
			@RequestParam String street,
			@RequestParam String postcode,
			@RequestParam String streetNumber,
			@RequestParam String localNumber,
			@RequestParam String phoneNumberFirst,
			@RequestParam String phoneNumberSecond,
			@RequestParam String fax) {
		if(bindResult.hasErrors())
			return "registerForm";
		else {
			Address address = new Address(city, voivodeship, county, country, street, postcode, streetNumber, localNumber);
			addressRepo.save(address);
			user.setAddress(address);
			Contact contact = new Contact(phoneNumberFirst, phoneNumberSecond, fax);
			contactRepo.save(contact);
			user.setContact(contact);
			userService.addWithDefaultRole(user);
			return "registerSuccess";
		}
	}
	
}