package com.codingdojo.pokemon.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.pokemon.models.Pokemon;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long> {
	
	List<Pokemon> findAll();
//	
	Optional<Pokemon> findById(Long id);
	
	
	
}
