package pl.scartout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.Address;
import pl.scartout.model.Contact;
import pl.scartout.model.User;
import pl.scartout.repo.AddressRepo;
import pl.scartout.repo.ContactRepo;
import pl.scartout.repo.UserRepo;
	 
@Controller
public class SettingsController {

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private UserRepo userRepo;
	private AddressRepo addressRepo;
	private ContactRepo contactRepo;
    
    @Autowired
    public SettingsController(UserRepo userRepo, AddressRepo addressRepo, ContactRepo contactRepo) {
    	this.userRepo = userRepo;
        this.addressRepo = addressRepo;
        this.contactRepo = contactRepo;
    }
    
    @GetMapping(path = "/settings", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCarts(Model model) {
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = userDetails.getUsername();
    	User user = userRepo.findByUsername(username);
    	model.addAttribute("user", user);
        return "settings";
    }
    
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(
				@RequestParam Long id, 
				@RequestParam String password, 
				@RequestParam String email, 
	    		@RequestParam String firstName, 
	    		@RequestParam String lastName,
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
    		password = passwordEncoder.encode(password);
    		userRepo.updateUser(id, password, email, firstName, lastName);
    		User user = userRepo.findById(id);
    		Contact contact = user.getContact();
    		Address address = user.getAddress();
    		Long contactId = contact.getId();
    		Long addressId = address.getId();
    		
    		contactRepo.updateContact(contactId, phoneNumberFirst, phoneNumberSecond, fax);
    		addressRepo.updateAddress(addressId, city, voivodeship, country, street, postcode, streetNumber, localNumber);
			return "redirect:/";
		}
	
	
}