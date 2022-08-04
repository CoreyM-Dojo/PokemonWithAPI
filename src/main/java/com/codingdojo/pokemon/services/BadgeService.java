package com.codingdojo.pokemon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.pokemon.models.Badge;
import com.codingdojo.pokemon.models.Trainer;
import com.codingdojo.pokemon.repositories.BadgeRepository;

@Service
public class BadgeService {

	@Autowired
	public BadgeRepository tRepo;
	
	// Get all Badges in the database
	public List<Badge> all() {
		return tRepo.findAll();
	}
	
	public List<Badge> unattained(Trainer t) {
		return tRepo.findByTrainersNotContains(t);
	}
	
	// Create a new Badge in the database
	public Badge create(Badge t) {
		return tRepo.save(t);
	}
	
	// Get one Badge out of the database
	public Badge find(Long id) {
		return tRepo.findById(id).orElse(null);
	}
	
	// Update a Badge in the database
	public Badge update(Badge t) {
		return tRepo.save(t);
	}
	
	public String destroy(Long id) {
		tRepo.deleteById(id);
		return "Badge removed from database";
	}
	
	
}
