package com.codingdojo.pokemon.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingdojo.pokemon.models.Pokemon;
import com.codingdojo.pokemon.repositories.PokemonRepository;

@Service
public class PokemonService {
	
	private final PokemonRepository pRepo;
	
	public PokemonService(PokemonRepository pRepo) {
		this.pRepo = pRepo;
	}
	
	
	public Iterable<Pokemon> all() {
		return pRepo.findAll();
	}
	
	public Pokemon create(Pokemon p) {
		return pRepo.save(p);
	}
	
	public Pokemon find(Long id) {
		return pRepo.findById(id).orElse(null);
	}
	
	public Pokemon update(Pokemon p) {
		return pRepo.save(p);
	}
	
	public String delete(Long id) {
		pRepo.deleteById(id);
		return "It was a success";
	}
	
}
