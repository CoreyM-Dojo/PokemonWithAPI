package com.codingdojo.pokemon.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.pokemon.models.Badge;
import com.codingdojo.pokemon.services.BadgeService;
import com.codingdojo.pokemon.services.TrainerService;

@Controller
@RequestMapping("/badge")
public class BadgeController {
	
	@Autowired
	public BadgeService bService;
	@Autowired
	public TrainerService tService;
	
	String[] gens = {"1st","2nd","3rd","4th","5th","6th", "7th", "8th"};
	String[] types = {"None","Fire", "Grass", "Electric", "Psychic", "Steel", "Ground", "Ghost", "Dark", "Dragon", "Ice", "Water", "Normal", "Fairy"};
	
	
	@RequestMapping("/new")
	public String addBadge(Model model, @ModelAttribute("badge") Badge badge) {
		model.addAttribute("gens", gens);
		model.addAttribute("types", types);
		model.addAttribute("allTrainers", tService.all() );
		
 		return "/badge/new.jsp";
	}
	
	@PostMapping("/create")
	public String createBadge(@Valid @ModelAttribute("badge") Badge badge,BindingResult result, Model model) {
			System.out.println("Sending data");
			
			model.addAttribute("gens", gens);
			model.addAttribute("types", types);
			
			if (result.hasErrors() ) {
				return "/badge/new.jsp";
			} else {
				
				bService.create(badge);
				return "redirect:/";
			}
			 
		
	}
	
	
	@GetMapping("/{id}")
	public String viewBadge(@PathVariable Long id, Model model) {
		Badge p = bService.find(id);
		model.addAttribute("poke", p);
		return "/badge/view.jsp";
		
		
	}
	
	@GetMapping("/edit/{id}")
	public String updateBadge(Model model, @ModelAttribute("badge") Badge badge, @PathVariable("id") Long id) {
		model.addAttribute("gens", gens);
		model.addAttribute("types", types);
		model.addAttribute("id",id);
		
		Badge BadgeToEdit = bService.find(id);
		model.addAttribute("bte",BadgeToEdit);
		
 		return "/badge/edit.jsp";
	}
	
	@PutMapping("/{id}")
	public String updateBadge(@Valid @ModelAttribute("Badge") Badge badge,BindingResult result, Model model, @PathVariable Long id) {
			
			model.addAttribute("gens", gens);
			model.addAttribute("types", types);
			model.addAttribute("id",id);
			
			if (result.hasErrors() ) {
				return "badge/edit.jsp";
			} else {
				
				bService.update(badge);
				return "redirect:/";
			}
			
			 
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteBadge(@PathVariable("id") Long id) {
		bService.destroy(id);
		return "redirect:/";
	}
}
