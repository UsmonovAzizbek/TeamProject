package com.example.teamproject.controller;

import com.example.teamproject.dto.RestaurantAddressDTO;
import com.example.teamproject.entity.Address;
import com.example.teamproject.entity.Restaurant;
import com.example.teamproject.repository.AddressRepository;
import com.example.teamproject.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class RestaurnatControler {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    AddressRepository addressRepository;

    @GetMapping(value = "/restaurant")
    public List<Restaurant> getRest(){
        return restaurantRepository.findAll();
    }

    @PostMapping(value = "/restaurant")
    public String addRest(@RequestBody RestaurantAddressDTO restaurantAddressDTO){
        boolean byRestName = restaurantRepository.existsByRestName(restaurantAddressDTO.getRestName());
        if (!byRestName) {
            Restaurant restaurant1 = new Restaurant();
            restaurant1.setRestName(restaurantAddressDTO.getRestName());
            Address address = new Address();
            address.setDistrict(restaurantAddressDTO.getDistrict());
            address.setStreet(restaurantAddressDTO.getStreet());
            addressRepository.save(address);
            restaurant1.setAddress(address);
            restaurantRepository.save(restaurant1);
            return "Save Restaurant";
        }else {
            return "Bunday nomdagi Restaurant mavjud!";
        }
    }

    @PutMapping(value = "/restaurant/{id}")
    public String editRest(@RequestBody RestaurantAddressDTO restaurantAddressDTO, @PathVariable Integer id){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()){
            Restaurant restaurant1 = optionalRestaurant.get();
            restaurant1.setRestName(restaurantAddressDTO.getRestName());
            Address address = new Address();
            address.setDistrict(restaurantAddressDTO.getDistrict());
            address.setStreet(restaurantAddressDTO.getStreet());
            addressRepository.save(address);
            restaurant1.setAddress(address);
            restaurantRepository.save(restaurant1);
        }
        return "Edit Restaurnat";
    }

    @DeleteMapping(value = "/restaurant/{id}")
    public String deleteRest(@RequestBody Restaurant restaurant, @PathVariable Integer id){
        restaurantRepository.deleteById(id);
        return "Delete Restaurant";
    }
}
