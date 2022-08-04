package com.codingdojo.pokemon.controllers;
import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codingdojo.pokemon.services.PokemonService;
import com.codingdojo.pokemon.controllers.ApiController;

@Controller
public class HomeController {
	
	@Autowired
	public PokemonService pService;
	
	String[] gens = {"1st","2nd","3rd","4th","5th","6th", "7th", "8th"};
	String[] types = {"None","Fire", "Grass", "Electric", "Psychic", "Steel", "Ground", "Ghost", "Dark", "Dragon", "Ice", "Water", "Normal", "Fairy"};
	
	
	@GetMapping("/")
	public String index(Model model) {
		ArrayList<Object> pokemon = new ArrayList<>();
		JSONObject json = new JSONObject(ApiController.getApi());
		System.out.println(json.getJSONArray("results"));
		
		for (int i = 0;i < json.getJSONArray("results").length(); i++) {
			JSONObject pokemonObject = json.getJSONArray("results").getJSONObject(i);
			pokemon.add(pokemonObject);
		}
//		String str = json.getString("name");
//		String pokeName = str.substring(0,1).toUpperCase() + str.substring(1);
		
//		model.addAttribute("pokeName", pokeName);
		model.addAttribute("pokemon", pokemon);
		return "index.jsp";
	}
	
	
	
 
}
