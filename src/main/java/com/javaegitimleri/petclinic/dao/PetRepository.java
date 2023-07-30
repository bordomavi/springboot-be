package com.javaegitimleri.petclinic.dao;

import java.util.List;

import com.javaegitimleri.petclinic.model.Pet;

public interface PetRepository {

	Pet findById(Long id);
	List<Pet> findByOwnerId(Long ownerId);
	
	void create(Pet Pet);
	Pet update(Pet Pet);
	void delete(Long id);
	void deleteByOwnerId(Long ownerId);
	
	
	
}
