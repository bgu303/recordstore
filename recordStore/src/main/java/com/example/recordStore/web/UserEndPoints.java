package com.example.recordStore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.recordStore.domain.SignUpForm;
import com.example.recordStore.domain.User;
import com.example.recordStore.domain.UserRepository;

@Controller
public class UserEndPoints {
	
	@Autowired
	private UserRepository repository;
	
	
	@GetMapping("/signup")
	public String signUp(Model model) {
		model.addAttribute("signupform", new SignUpForm());
		return "signup";
	}
	
	@PostMapping("/createuser")
	public String createUser(@Valid @ModelAttribute("signupform") SignUpForm signUpForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			if (signUpForm.getPassword().equals(signUpForm.getPasswordCheck())) {
				String password = signUpForm.getPassword();
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String hashedPassword = encoder.encode(password);
				
				User newUser = new User();
				newUser.setPasswordHash(hashedPassword);
				newUser.setUsername(signUpForm.getUsername());
				newUser.setEmail(signUpForm.getEmail());
				newUser.setRole("USER");
				if (repository.findByUsername(signUpForm.getUsername()) == null) {
					repository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
			}
			
			} else {
				return "signup";
		}
		return "redirect:/records";
	}

}
