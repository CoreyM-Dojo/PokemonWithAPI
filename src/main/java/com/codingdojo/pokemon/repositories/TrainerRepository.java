package com.codingdojo.pokemon.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.pokemon.models.Trainer;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Long> {
	
	List<Trainer> findAll();
		
}
