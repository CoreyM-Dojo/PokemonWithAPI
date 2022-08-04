package com.codingdojo.pokemon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.pokemon.models.Badge;
import com.codingdojo.pokemon.models.Trainer;

@Repository
public interface BadgeRepository extends CrudRepository<Badge, Long> {
	
	List<Badge> findAll();
	
	List<Badge> findByTrainersNotContains(Trainer t);
	
	
	
}
