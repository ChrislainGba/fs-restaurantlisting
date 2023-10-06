package com.xl1.fsrestaurantlisting.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.xl1.fsrestaurantlisting.dto.RestaurantDTO;
import com.xl1.fsrestaurantlisting.entity.Restaurant;
import com.xl1.fsrestaurantlisting.mapper.RestaurantMapper;
import com.xl1.fsrestaurantlisting.repository.RestaurantRepository;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;

	public List<RestaurantDTO> findAllRestaurants() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		//map to list of DTO	
		return restaurants.stream().map(restaurant -> RestaurantMapper.INSTANTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());
	}

	public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
		Restaurant restaurantSaved = restaurantRepository.save(RestaurantMapper.INSTANTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
		return RestaurantMapper.INSTANTANCE.mapRestaurantToRestaurantDTO(restaurantSaved);
	}

	public ResponseEntity<RestaurantDTO> fetchRestaurantById(Long id) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		if(restaurant.isPresent()) {
			return new ResponseEntity<RestaurantDTO>(RestaurantMapper.INSTANTANCE.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

}
