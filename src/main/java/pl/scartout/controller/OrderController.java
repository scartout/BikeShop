package pl.scartout.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import pl.scartout.model.Address;
import pl.scartout.model.Order;
import pl.scartout.model.Product;
import pl.scartout.model.User;
import pl.scartout.repo.ProductRepo;
import pl.scartout.repo.UserRepo;
import pl.scartout.repo.OrderRepo;

@Controller
public class OrderController {
	
	final String INVOICE_FOLDER = "D:/";
	
	private ProductRepo productRepo;
	private UserRepo userRepo;
	private OrderRepo orderRepo;
	 
    @Autowired
    public OrderController(ProductRepo productRepo, UserRepo userRepo, OrderRepo orderRepo) {
        this.productRepo= productRepo;
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
    }
    
    @GetMapping(path = "/cart", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCarts(Model model) {
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = userDetails.getUsername();
    	User user = userRepo.findByUsername(username);
    	List <Order> orders = orderRepo.findByUserAndOrderDateIsNull(user);
    	model.addAttribute("orders", orders);
        return "cart";
    }
    
    @GetMapping(path = "/ordersedit", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOrders(Model model) {
    	List <Order> orders = orderRepo.findByOrderDateIsNotNull();
    	model.addAttribute("orders", orders);
        return "ordersedit";
    }
    
    @GetMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOrdersByUser(Model model) {
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = userDetails.getUsername();
    	User user = userRepo.findByUsername(username);
    	List <Order> orders = orderRepo.findByUserAndOrderDateIsNotNull(user);
    	model.addAttribute("orders", orders);
        return "orders";
    }
    
    @PostMapping(path = "/addtocart", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addToCart(Model model, @RequestParam Long productId, @RequestParam int quantity) {
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username = userDetails.getUsername();
    	User user = userRepo.findByUsername(username);
    	Product product = productRepo.findById(productId);
    	Order order = new Order(quantity);
    	order.setProduct(product);
    	order.setUser(user);
    	orderRepo.save(order);
        return "redirect:/product?id="+productId;
    }
    
    @PostMapping("/confirmOrder")
	public String confirmOrder(@RequestParam Long id, @RequestParam int quantity, @RequestParam double price) {
    	double total = price*quantity;
    	orderRepo.confirmOrder(id, new Date(), quantity, total);
		return "redirect:/cart";
	}
    
    @PostMapping("/deleteOrder")
	public String confirmOrder(@RequestParam Long id) {
    	orderRepo.deleteOrder(id);
		return "redirect:/cart";
	}
    
    @PostMapping("/confirmApproval")
	public String confirmApproval(@RequestParam Long id) {
    	Order order = orderRepo.findById(id);
    	String invoiceNumber = id + "-" + new SimpleDateFormat("yyyy").format(order.getOrderDate());
    	orderRepo.confirmApproval(id, invoiceNumber, new Date());
		return "redirect:/ordersedit";
	}
    
    @PostMapping("/confirmShipping")
	public String confirmShipping(@RequestParam Long id) {
    	orderRepo.confirmShipping(id, new Date());
		return "redirect:/ordersedit";
	}
    
    @PostMapping("/confirmComplete")
	public String confirmComplete(@RequestParam Long id) {
    	orderRepo.confirmComplete(id, new Date());
		return "redirect:/ordersedit";
	}
    
    @PostMapping("/cancelOrder")
	public String cancelOrder(@RequestParam Long id) {
    	orderRepo.deleteOrder(id);
		return "redirect:/ordersedit";
	}
    
    @PostMapping("/printInvoice")
	public String printInvoice(@RequestParam Long id) throws DocumentException, IOException {
    	Order order = orderRepo.findById(id);
    	User user = order.getUser();
    	Address address = user.getAddress();
    	Product product = order.getProduct();
    	
    	String FILE = INVOICE_FOLDER + "Invoice " + order.getId() + "-" + (new SimpleDateFormat("yyyy").format(order.getOrderDate()))+".pdf";
		
    	BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
    	Font helvetica48 = new Font(helvetica, 48);
    	Font helvetica16 = new Font(helvetica, 16);
    	Font helvetica14 = new Font(helvetica, 14);
    	Font helvetica12 = new Font(helvetica, 12);
    	Font helvetica10 = new Font(helvetica, 10);

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
		} catch (FileNotFoundException | DocumentException e1) {
			e1.printStackTrace();
		}
		document.open();
		      
		document.addTitle("Invoice");
		document.addAuthor("Bike Shop");
		document.addCreator("Bike Shop");
		      
		Paragraph p = new Paragraph("Invoice no: " + order.getId() + "/" + (new SimpleDateFormat("yyyy").format(order.getOrderDate())),
			          helvetica16);
		DottedLineSeparator dottedline = new DottedLineSeparator();
		dottedline.setOffset(-2);
		dottedline.setGap(2f);
		p.add(dottedline);
		try {
			document.add(p);
		} catch (DocumentException e) {
				e.printStackTrace();
		}
		      
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 4);
		preface.add(new Paragraph("BikeShop", helvetica48));

		addEmptyLine(preface, 2);
		preface.add(new Paragraph("Invoice date: "+ (new SimpleDateFormat("dd/MM/yyyy").format(order.getOrderDate())), helvetica12));
		      
		addEmptyLine(preface, 2);
		preface.add(new Paragraph("Ship to:", helvetica14));
		      
		addEmptyLine(preface, 0);
		preface.add(new Paragraph(user.getFirstName()+" "+user.getLastName(), helvetica10));
		      
		addEmptyLine(preface, 0);
		if (address.getLocalNumber()==null) {
		   preface.add(new Paragraph(address.getStreetNumber()+" "+address.getStreet(), helvetica10));}
		else preface.add(new Paragraph(address.getStreetNumber()+"/"+address.getLocalNumber()+" "+address.getStreet(), helvetica10));
		      
		addEmptyLine(preface, 0);
		preface.add(new Paragraph(address.getCity()+", "+address.getPostcode(), helvetica10));
		      
		addEmptyLine(preface, 0);
		preface.add(new Paragraph(address.getCountry(), helvetica10));
		      
		addEmptyLine(preface, 2);
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		table.addCell("Product");
		table.addCell("Price");
		table.addCell("Price net");
		table.addCell("Vat");
		table.addCell("Quantity");
		table.addCell("Amount");
		PdfPTable table2 = new PdfPTable(6);
		table2.setWidthPercentage(100);
		table2.addCell(product.getName());
		table2.addCell(Double.toString(product.getPrice())+" PLN");
		table2.addCell(Double.toString(product.getPriceNet())+" PLN");
		table2.addCell(Double.toString(product.getVat())+" %");
		table2.addCell(Integer.toString(order.getQuantity()));
		table2.addCell(Double.toString(order.getTotal())+" PLN");
		        
		try {
			document.add(preface);
			document.add(table);
			document.add(table2);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		      
		document.close();
		return "redirect:/ordersedit";
	}
    
	private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	      paragraph.add(new Paragraph(" "));
	    }
	  }
    
}
    
    