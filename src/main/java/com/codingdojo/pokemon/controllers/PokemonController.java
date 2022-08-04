package com.codingdojo.pokemon.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.pokemon.models.Pokemon;
import com.codingdojo.pokemon.services.PokemonService;
import com.codingdojo.pokemon.services.TrainerService;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {
	
	@Autowired
	public PokemonService pService;
	@Autowired
	public TrainerService tService;
	
	String[] gens = {"1st","2nd","3rd","4th","5th","6th", "7th", "8th"};
	String[] types = {"None","Fire", "Grass", "Electric", "Psychic", "Steel", "Ground", "Ghost", "Dark", "Dragon", "Ice", "Water", "Normal", "Fairy"};
	
	
	@RequestMapping("/new")
	public String addPokemon(Model model, @ModelAttribute("pokemon") Pokemon pokemon) {
		model.addAttribute("gens", gens);
		model.addAttribute("types", types);
		model.addAttribute("allTrainers", tService.all() );
		
 		return "/pokemon/new.jsp";
	}
	
	@PostMapping("/create")
	public String createPokemon(@Valid @ModelAttribute("pokemon") Pokemon pokemon,BindingResult result, HttpSession session,
			@RequestParam(value="name") String name,
			@RequestParam(value="generation") String generation, 
			@RequestParam(value="type1") String type1, 
			@RequestParam(value="type2") String type2, Model model) {	
			session.setAttribute("name", name);
			session.setAttribute("generation", generation);
			session.setAttribute("type1", type1);
			session.setAttribute("type2", type2);
			System.out.println("Sending data");
			
			model.addAttribute("gens", gens);
			model.addAttribute("types", types);
			
			if (result.hasErrors() ) {
				return "/pokemon/new.jsp";
			} else {
				
				pService.create(pokemon);
				return "redirect:/";
			}
			 
		
	}
	
	
	@GetMapping("/search")
	public String viewPokemon(@RequestParam String pokemon, Model model, RedirectAttributes flashMessages, HttpSession session) {
		String test = ApiController.getPokemon(pokemon.toLowerCase());
		if (test.equals("Something went wrong") || test.equals("Not Found")) {
			flashMessages.addFlashAttribute("message","Check your spelling, that may have been an incorrect entry");
			return "redirect:/pokemon/search?pokemon=" + session.getAttribute("pokemon");
		}
		session.setAttribute("pokemon", pokemon );
		JSONObject json = new JSONObject(test);
		ArrayList<JSONObject> abilities = new ArrayList<>();
		ArrayList<JSONObject> stats = new ArrayList<>();
		
		JSONArray abilityArray = json.getJSONArray("abilities");
		JSONArray statArray = json.getJSONArray("stats");
		model.addAttribute("poke", json);
		
		for (int i = 0; i < abilityArray.length(); i++ ) {
			JSONObject ability = abilityArray.getJSONObject(i);
			abilities.add(ability);
		}
		for (int i = 0; i < statArray.length(); i++ ) {
			JSONObject stat = statArray.getJSONObject(i);
			stats.add(stat);
		}
		model.addAttribute("abilities", abilities);
		model.addAttribute("stats", stats);
		return "/pokemon/view.jsp";
		
		
	}
	
	@GetMapping("/edit/{id}")
	public String updatePokemon(Model model, @ModelAttribute("pokemon") Pokemon pokemon, @PathVariable("id") Long id) {
		model.addAttribute("gens", gens);
		model.addAttribute("types", types);
		model.addAttribute("id",id);
		
		Pokemon pokemonToEdit = pService.find(id);
		model.addAttribute("pte",pokemonToEdit);
		
 		return "/pokemon/edit.jsp";
	}
	
	@PutMapping("/{id}")
	public String updatePokemon(@Valid @ModelAttribute("pokemon") Pokemon pokemon,BindingResult result, Model model, @PathVariable Long id) {
			
			model.addAttribute("gens", gens);
			model.addAttribute("types", types);
			model.addAttribute("id",id);
			
			if (result.hasErrors() ) {
				return "pokemon/edit.jsp";
			} else {
				
				pService.update(pokemon);
				return "redirect:/";
			}
			
			 
		
	}
	
	@DeleteMapping("/{id}")
	public String deletePokemon(@PathVariable("id") Long id) {
		pService.delete(id);
		return "redirect:/";
	}
}
