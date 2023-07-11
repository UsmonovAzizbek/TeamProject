package com.example.teamproject.controller;

import com.example.teamproject.dto.FoodDTO;
import com.example.teamproject.dto.RestaurantAddressDTO;
import com.example.teamproject.entity.Address;
import com.example.teamproject.entity.Food;
import com.example.teamproject.entity.Restaurant;
import com.example.teamproject.repository.FoodRepository;
import com.example.teamproject.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping
public class FoodController {
    @Autowired
    FoodRepository foodRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @PostMapping(value = "/food")
    public String addFood(@RequestBody FoodDTO foodDTO){
        Food food  = new Food();
        food.setFoodName(foodDTO.getFoodName());
        food.setPrice(foodDTO.getPrice());
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(foodDTO.getRestaurant_id());
        if (optionalRestaurant.isPresent()){
            food.setRestaurant(optionalRestaurant.get());
            foodRepository.save(food);
            return "save food";
        }else {
            return "Restaurant not found";
        }

    }

    @GetMapping(value = "/food/{restaurant_id}")
    public List<Food> getFood(@PathVariable Integer restaurant_id){
        List<Food> byRestaurantId = foodRepository.findAllByRestaurantId(restaurant_id);
        return byRestaurantId;
    }
    @GetMapping(value = "/food")
    public List<Food> getAllFood(){
        return foodRepository.findAll();
    }

    @PutMapping(value = "/food/{id}")
    public String editFood(@RequestBody FoodDTO foodDTO, @PathVariable Integer id){
        Optional<Food> optionalFood = foodRepository.findById(id);
        if (optionalFood.isPresent()){
            Food food = optionalFood.get();
            food.setFoodName(foodDTO.getFoodName());
            food.setPrice(foodDTO.getPrice());
            foodRepository.save(food);
        }
        return "Edit Food";
    }

    @DeleteMapping(value = "/food/{id}")
    public String deleteFood(@RequestBody Food food, @PathVariable Integer id){
        foodRepository.deleteById(id);
        return "Delete Food";
    }

}
