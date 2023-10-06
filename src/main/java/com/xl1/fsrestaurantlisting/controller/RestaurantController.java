package com.xl1.fsrestaurantlisting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xl1.fsrestaurantlisting.dto.RestaurantDTO;
import com.xl1.fsrestaurantlisting.entity.Restaurant;
import com.xl1.fsrestaurantlisting.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("fetchAllRestaurants")
	public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurant(){
		List<RestaurantDTO> restaurants = restaurantService.findAllRestaurants();
		return new ResponseEntity<>(restaurants,HttpStatus.OK);
	}
	
	@PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO restaurantAdded = restaurantService.addRestaurantInDB(restaurantDTO);
        return new ResponseEntity<>(restaurantAdded, HttpStatus.CREATED);
    }

    @GetMapping("fetchById/{id}")
    public ResponseEntity<RestaurantDTO> findRestaurantById(@PathVariable Long id) {
       return restaurantService.fetchRestaurantById(id);
    }
}
