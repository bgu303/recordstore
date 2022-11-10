package com.example.recordStore.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.recordStore.domain.OrderForm;
import com.example.recordStore.domain.Rec;
import com.example.recordStore.domain.RecRepository;

@Controller
public class SessionEndPoints {

	@Autowired
	private HttpSession session;

	@Autowired
	private RecRepository repository;
	
	@Autowired
	private JavaMailSender mailSender;

	static List<Rec> shoppingCart = new ArrayList<Rec>();
	
	public static void clearShoppingCart() {
		shoppingCart.clear();
	}
	
	int totalPrice = 0;

	@GetMapping("/addtocart/{id}")
	@ResponseBody
	private int addToCart(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, HttpStatus httpStatus) {
		session = request.getSession();
		shoppingCart = (ArrayList<Rec>) session.getAttribute("SHOPPINGSESSION");
		boolean checker = false;
		totalPrice = 0;

		if (shoppingCart == null) {
			shoppingCart = new ArrayList<>();
		}

		ArrayList<Rec> shoppingList = (ArrayList<Rec>) shoppingCart;

		if (shoppingList.size() == 0) {
			shoppingList.add((Rec) repository.findById(id).get());
			checker = true;
		} else {
			for (int i = 0; i < shoppingList.size(); i++) {
				if (shoppingList.get(i).getId() == id) {
					checker = true;
					response.setStatus(400);
				} 
			}
		}

		if (checker == false && shoppingList.size() != 0) {
			shoppingList.add((Rec) repository.findById(id).get());
		}
		
		for(Rec rec : shoppingList) {
			totalPrice += rec.getPrice();
		}

		session.setAttribute("SHOPPINGSESSION", shoppingCart);
		System.out.println(session.getAttribute("SHOPPINGSESSION"));

		return response.getStatus();
	}

	@GetMapping("/shoppingcart")
	public String showShoppingCart(Model model) {
		model.addAttribute("shoppingcart", shoppingCart);
		model.addAttribute("orderform", new OrderForm());
		
		if (shoppingCart.size() == 0) {
			return "redirect:/records";
		}
		
		model.addAttribute("totalprice", totalPrice);
		return "shoppingcart";
	}
	
	@GetMapping("/deletefromshoppingcart/{id}")
	public String deleteFromShoppingCart(@PathVariable("id") Long id, Model model) {
		totalPrice = 0;

		for (int i = 0; i < shoppingCart.size(); i++) {
			if (shoppingCart.get(i).getId() == id) {
				shoppingCart.remove(shoppingCart.get(i));
			}
		}
		
		for (Rec rec : shoppingCart) {
			totalPrice += rec.getPrice();
		}

		return "redirect:/shoppingcart";
	}
	
	@PostMapping("/sendorder")
	public String sendOrder(@ModelAttribute("orderform") OrderForm orderform) throws InterruptedException {
		
		DecimalFormat twoDecimals = new DecimalFormat("0.00");
		String recordString = "";
		String totalPriceDouble = twoDecimals.format(totalPrice);
		
		for (int i = 0; i < shoppingCart.size(); i++) {
			recordString += shoppingCart.get(i).getArtist() + "   -   " + shoppingCart.get(i).getTitle() + "   -   " + shoppingCart.get(i).getDiscogs() + "   -   " + shoppingCart.get(i).getPrice() + "\n";
		}
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("jukkavesanto93@gmail.com");
		message.setText(orderform.toString() + "\n \n" + recordString + "\n" + "Yhteismäärä: " + shoppingCart.size() + "\n" + 
		"Yhteensä: " + totalPriceDouble + " Euroa \n+Postikulut");
		message.setSubject("Levytilaus");
		
		Thread emailThread = new Thread(() -> {
			if (!shoppingCart.isEmpty()) {
				mailSender.send(message);
			}
		});
				
		Thread clearThread = new Thread(() -> {
			shoppingCart.clear();
		});
		
		emailThread.start();
		clearThread.start();
		
		emailThread.join();
		clearThread.join();
		
		return "redirect:/orderconfirmation";
	}
	
	@GetMapping("/orderconfirmation")
	public String getOrderConf() {
		return "orderconfirmation";
	}
	
}
