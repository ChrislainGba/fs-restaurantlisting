package com.xl1.fsrestaurantlisting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.xl1.fsrestaurantlisting.dto.RestaurantDTO;
import com.xl1.fsrestaurantlisting.entity.Restaurant;

@Mapper
public interface RestaurantMapper {
	RestaurantMapper INSTANTANCE = Mappers.getMapper(RestaurantMapper.class);
	
	Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);
	RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);
}
