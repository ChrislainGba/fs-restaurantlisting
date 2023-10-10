package com.xl1.fsrestaurantlisting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.xl1.fsrestaurantlisting.dto.RestaurantDTO;
import com.xl1.fsrestaurantlisting.entity.Restaurant;
import com.xl1.fsrestaurantlisting.mapper.RestaurantMapper;
import com.xl1.fsrestaurantlisting.repository.RestaurantRepository;

public class RestaurantServiceTest {
	
	@InjectMocks
	private RestaurantService restaurantService;
	
	@Mock
	private RestaurantRepository restaurantRepository;
	
	@BeforeEach
	public void setUp() {
		//open Mock then Mockito will work perfectly
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testFetchAllRestaurants() {
		// Mock the service behavior
        List<Restaurant> mockRestaurants = Arrays.asList(
                new Restaurant(1L, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
                new Restaurant(2L, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );
        when(restaurantRepository.findAll()).thenReturn(mockRestaurants);

        // Call the controller method
        List<RestaurantDTO> listRestaurantDTO = restaurantService.findAllRestaurants();

        // Verify the response
        assertEquals(mockRestaurants.size(), listRestaurantDTO.size());
        for (int i = 0; i < mockRestaurants.size(); i++) {
			RestaurantDTO expectedDTO = RestaurantMapper.INSTANTANCE.mapRestaurantToRestaurantDTO(mockRestaurants.get(i));
			assertEquals(expectedDTO, listRestaurantDTO.get(i));
		}
     // Verify that the service method was called
        verify(restaurantRepository, times(1)).findAll();
	}
	
	 @Test
    public void testAddRestaurantInDB() {
        // Create a mock restaurant to be saved
        RestaurantDTO mockRestaurantDTO = new RestaurantDTO(1L, "Restaurant 1", "Address 1", "city 1", "Desc 1");
        Restaurant mockRestaurant = RestaurantMapper.INSTANTANCE .mapRestaurantDTOToRestaurant(mockRestaurantDTO);

        // Mock the repository behavior
        when(restaurantRepository.save(mockRestaurant)).thenReturn(mockRestaurant);

        // Call the service method
        RestaurantDTO savedRestaurantDTO = restaurantService.addRestaurantInDB(mockRestaurantDTO);

        // Verify the result
        assertEquals(mockRestaurantDTO, savedRestaurantDTO);

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).save(mockRestaurant);
    }

    @Test
    public void testFetchRestaurantById_ExistingId() {
        // Create a mock restaurant ID
        Long mockRestaurantId = 1L;

        // Create a mock restaurant to be returned by the repository
        Restaurant mockRestaurant = new Restaurant(1L, "Restaurant 1", "Address 1", "city 1", "Desc 1");

        // Mock the repository behavior
        when(restaurantRepository.findById(mockRestaurantId)).thenReturn(Optional.of(mockRestaurant));

        // Call the service method
        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurantId, response.getBody().getId());

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findById(mockRestaurantId);
    }

    @Test
    public void testFetchRestaurantById_NonExistingId() {
        // Create a mock non-existing restaurant ID
        Long mockRestaurantId = 1L;

        // Mock the repository behavior
        when(restaurantRepository.findById(mockRestaurantId)).thenReturn(Optional.empty());

        // Call the service method
        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findById(mockRestaurantId);
    }
	
	

}
