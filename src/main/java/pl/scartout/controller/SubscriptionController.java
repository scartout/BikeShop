package pl.scartout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import pl.scartout.model.Subscription;
import pl.scartout.repo.SubscriptionRepo;
	 
@Controller
public class SubscriptionController {
    
	private SubscriptionRepo subscriptionRepo;
    
    @Autowired
    public SubscriptionController(SubscriptionRepo subscriptionRepo) {
        this.subscriptionRepo = subscriptionRepo;
    }

	@PostMapping("/subscribe")
	public String addSubscription(@RequestParam(required=false) String email) {
		if(email!="") {
			Subscription subscription = new Subscription(email);
			subscriptionRepo.save(subscription);
		}
		return "redirect:/";
	}
	
}