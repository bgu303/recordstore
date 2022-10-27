package com.example.recordStore.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.recordStore.domain.Rec;
import com.example.recordStore.domain.RecRepository;

@Controller
public class SessionEndPoints {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private RecRepository repository;
	
	List<Rec> shoppingCart = new ArrayList<Rec>();
	
	@GetMapping("/addtocart/{id}")
	private String addToCart(@PathVariable("id") Long id, HttpServletRequest request) {
		session = request.getSession();
		 shoppingCart = (ArrayList<Rec>) session.getAttribute("SHOPPINGSESSION");
		
		if (shoppingCart == null) {
			shoppingCart = new ArrayList<>();
		}
		ArrayList<Rec> shoppingList = (ArrayList<Rec>) shoppingCart;
		shoppingList.add((Rec) repository.findById(id).get());
		session.setAttribute("SHOPPINGSESSION", shoppingCart);
		return "redirect:../records";
	}
	
	@GetMapping("/shoppingcart")
	public String showShoppingCart(Model model) {
		model.addAttribute("shoppingcart", shoppingCart);
		return "shoppingcart";
	}
}
