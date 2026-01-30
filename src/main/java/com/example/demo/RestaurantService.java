package com.example.demo;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
	
	private final RestaurantRepo repo;
	
	public RestaurantService(RestaurantRepo repo) {
		this.repo = repo;
		
	}
	
	public Restaurant addRestaurant(Restaurant restaurant) {
		return repo.save(restaurant);
		
	}
	
	public List<Restaurant> getAllRestaurant(){
		return repo.findAll();
		
	}
	
	public Restaurant getRestaurantById(Long id) {
	    return repo.findById(id).orElseThrow(() ->
	        new ResponseStatusException(HttpStatus.NOT_FOUND,"Restaurant not found with id: " + id));
	}
	
	public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
		Restaurant existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
		existing.setName(restaurant.getName());
		existing.setLocation(restaurant.getLocation());
		existing.setCuisine(restaurant.getCuisine());
		existing.setRating(restaurant.getRating());
		return repo.save(existing);	
	}
	
	public void deleteRestaurant(Long id) {
		repo.deleteById(id);
	}

}
