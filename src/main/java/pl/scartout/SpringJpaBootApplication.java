package pl.scartout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.scartout.model.Category;
import pl.scartout.model.Role;
import pl.scartout.repo.CategoryRepo;
import pl.scartout.repo.RoleRepo;

@SpringBootApplication
public class SpringJpaBootApplication{
	
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringJpaBootApplication.class, args);

        RoleRepo roleRepo = ctx.getBean(RoleRepo.class);
        if (roleRepo.findByRole("admin") == null) {
        	Role role1 = new Role(1L, "admin");
        	roleRepo.save(role1);}
        if (roleRepo.findByRole("user") == null) {
        	Role role2 = new Role(2L, "user");
        	roleRepo.save(role2);}
        
        CategoryRepo categoryRepo = ctx.getBean(CategoryRepo.class);
        if (categoryRepo.findByName("Road") == null) {
        	Category category1 = new Category(1L, "Road");
        	categoryRepo.save(category1);}

        if (categoryRepo.findByName("MTB") == null) {
        	Category category2 = new Category(2L, "MTB");
        	categoryRepo.save(category2);}

        if (categoryRepo.findByName("Cross/Trekking") == null) {
        	Category category3 = new Category(3L, "Cross/Trekking");
        	categoryRepo.save(category3);}

        if (categoryRepo.findByName("City") == null) {
        	Category category4 = new Category(4L, "City");
        	categoryRepo.save(category4);}
        
        if (categoryRepo.findByName("Accesories") == null) {
        	Category category5 = new Category(5L, "Accesories");
        	categoryRepo.save(category5);}

        
    }
}