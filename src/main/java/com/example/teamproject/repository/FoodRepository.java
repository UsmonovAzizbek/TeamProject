package com.example.teamproject.repository;

import com.example.teamproject.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> findAllByRestaurantId(Integer restaurantId);
}
