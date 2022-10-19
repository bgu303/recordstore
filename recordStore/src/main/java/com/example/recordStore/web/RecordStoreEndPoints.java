package com.example.recordStore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.recordStore.domain.Rec;
import com.example.recordStore.domain.RecRepository;

@Controller
public class RecordStoreEndPoints {

	@Autowired
	private RecRepository repository;
	
	@GetMapping("/records") 
	public String recordController(Model model) {
		model.addAttribute("records", repository.findAll());
		return "recordlist";
	}
	
	@GetMapping("/addrecord")
	public String addRecord(Model model) {
		model.addAttribute("record", new Rec());
		return "addrecord";	
	}
	
	@PostMapping("/saverecord")
	public String saveRecord(Rec record) {
		repository.save(record);
		return "redirect:records";
	}
	}

