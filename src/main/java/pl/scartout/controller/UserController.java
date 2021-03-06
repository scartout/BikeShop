package pl.scartout.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import pl.scartout.model.Address;
import pl.scartout.model.Contact;
import pl.scartout.model.User;
import pl.scartout.repo.AddressRepo;
import pl.scartout.repo.ContactRepo;
import pl.scartout.repo.UserRepo;
import pl.scartout.service.UserService;
	 
@Controller
public class UserController {

	private PasswordEncoder passwordEncoder;
	private UserService userService;
	private UserRepo userRepo;
	private AddressRepo addressRepo;
	private ContactRepo contactRepo;
    
    @Autowired
    public void setUserService(PasswordEncoder passwordEncoder, UserService userService, UserRepo userRepo, AddressRepo addressRepo, ContactRepo contactRepo) {
        this.passwordEncoder = passwordEncoder;
    	this.userService = userService;
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
        this.contactRepo = contactRepo;
    }
    
    @RequestMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public String home(Model model) {
    	model.addAttribute("user", new User());
        return "registerForm";
    }
    
    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String homeLogin(Model model) {
    	model.addAttribute("user", new User());
        return "login";
    }
	
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
	public String createUser(@ModelAttribute @Valid User user,
			BindingResult bindResult,
			@RequestParam String city,
			@RequestParam String voivodeship,
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
			if (userRepo.findByUsername(user.getUsername())==null) {
				Address address = new Address(city, voivodeship, country, street, postcode, streetNumber, localNumber);
				addressRepo.save(address);
				user.setAddress(address);
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				Contact contact = new Contact(phoneNumberFirst, phoneNumberSecond, fax);
				contactRepo.save(contact);
				user.setContact(contact);
				userService.addWithDefaultRole(user);
				return "registerSuccess";
			}
			else return "registerForm";
		}
	}
	
}