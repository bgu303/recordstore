package com.example.recordStore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.recordStore.domain.Rec;
import com.example.recordStore.domain.RecRepository;

@Controller
public class RecordStoreEndPoints {

	@Autowired
	private RecRepository repository;
	
	//Shows the recordlist
	@GetMapping("/records") 
	public String recordController(Model model) {
		model.addAttribute("records", repository.findAll());
		return "recordlist";
	}
	
	//Opens a page where you can add records to database
	@GetMapping("/addrecord")
	public String addRecord(Model model) {
		model.addAttribute("record", new Rec());
		return "addrecord";	
	}
	
	//Saves the record to the database
	@PostMapping("/saverecord")
	public String saveRecord(Rec record) {
		repository.save(record);
		return "redirect:records";
	}
	
	//Deletes the chosen record from database
	@GetMapping("/delete/{id}")
	public String deleteRecord(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../records";
	}
	
	@GetMapping("/update/{id}")
	public String updateRecord(@PathVariable("id") Long id, Model model) {
		model.addAttribute("record", repository.findById(id));
		return "updaterecord";
	}
}

