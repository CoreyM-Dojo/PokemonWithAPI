package com.codingdojo.pokemon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.pokemon.models.Badge;
import com.codingdojo.pokemon.models.Trainer;
import com.codingdojo.pokemon.repositories.TrainerRepository;

@Service
public class TrainerService {
	
	@Autowired
	BadgeService bService;

	@Autowired
	public TrainerRepository tRepo;
	
	// Get all trainers in the database
	public List<Trainer> all() {
		return tRepo.findAll();
	}
	
	// Create a new trainer in the database
	public Trainer create(Trainer t) {
		return tRepo.save(t);
	}
	
	// Get one trainer out of the database
	public Trainer find(Long id) {
		return tRepo.findById(id).orElse(null);
	}
	
	// Update a trainer in the database
	public Trainer update(Trainer t) {
		return tRepo.save(t);
	}
	
	public String destroy(Long id) {
		tRepo.deleteById(id);
		return "Trainer removed from database";
	}
	
	public String addToBadges(Long trainerId, Long badgeId) {
		Trainer t = find(trainerId);
		Badge b = bService.find(badgeId);
		t.getBadges().add(b);
		tRepo.save(t);
		return "Badges updated";
	}
	
	public String removeFromBadges(Long trainerId, Long badgeId) {
		Trainer t = find(trainerId);
		Badge b = bService.find(badgeId);
		t.getBadges().remove(b);
		tRepo.save(t);
		return "Badges updated";
	}
}
