package com.xl1.fsrestaurantlisting.dto;

import com.xl1.fsrestaurantlisting.entity.Restaurant;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RestaurantDTO {
	private Long id;
	private String name;
	private String city;
	private String address;
	private String restaurantDescription;
}
