package com.codingdojo.pokemon.controllers;

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

import com.codingdojo.pokemon.models.Trainer;
import com.codingdojo.pokemon.services.BadgeService;
import com.codingdojo.pokemon.services.TrainerService;

@Controller
@RequestMapping("/trainer")
public class TrainerController {

	@Autowired
	public TrainerService tService;
	@Autowired
	public BadgeService bService;
	
	@GetMapping("/new")
	public String addTrainer(@ModelAttribute("trainer") Trainer trainer) {
		return "/trainer/new.jsp";
	}
	
	@PostMapping("/create")
	public String addTrainer(@Valid @ModelAttribute("trainer") Trainer trainer, BindingResult result ) {
		
		if (result.hasErrors()) {
			return "trainer/new.jsp";
		}
		tService.create(trainer);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String viewTrainer(@PathVariable Long id, Model model) {
		Trainer t = tService.find(id);
		model.addAttribute("trainer", t);
		model.addAttribute("allBadges", bService.all());
		model.addAttribute("uncollected", bService.unattained(t));
		return "trainer/view.jsp";
	}
	
	@GetMapping("/edit/{id}")
	public String addTrainer(Model model, @PathVariable Long id ) {
		model.addAttribute("tte", tService.find(id));
		return "trainer/edit.jsp";
	}
	
	@PutMapping("/{id}")
	public String editTrainer(@Valid @ModelAttribute("trainer") Trainer trainer, BindingResult result, Model model, @PathVariable Long id ) {
		
		if (result.hasErrors()) {
			model.addAttribute("tte", tService.find(id));
			return "trainer/edit.jsp";
		}
		tService.update(trainer);
		return "redirect:/";
	}
	
	@DeleteMapping("/{id}")
	public String deleteTrainer(@PathVariable Long id) {
		tService.destroy(id);
		return "redirect:/";
	}
	
	@PostMapping("/addBadge/{trainerId}")
	public String addBadge(@PathVariable Long trainerId, @RequestParam("badgeId") Long badgeId) {
		tService.addToBadges(trainerId,badgeId);
		return "redirect:/trainer/" + trainerId;
	}
	
	@GetMapping("/removeBadge/{trainerId}/{badgeId}")
	public String removeBadge(@PathVariable Long trainerId, @PathVariable Long badgeId) {
		tService.removeFromBadges(trainerId, badgeId);
		return "redirect:/trainer/" + trainerId;
	}
	
}
