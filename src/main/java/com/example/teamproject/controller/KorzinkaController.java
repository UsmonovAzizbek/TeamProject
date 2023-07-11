package com.example.teamproject.controller;

import com.example.teamproject.dto.FoodDTO;
import com.example.teamproject.dto.KorzinkaFoodDTO;
import com.example.teamproject.entity.Food;
import com.example.teamproject.entity.Korzinka;
import com.example.teamproject.repository.FoodRepository;
import com.example.teamproject.repository.KorzinkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class KorzinkaController {
    @Autowired
    KorzinkaRepository korzinkaRepository;

    @Autowired
    FoodRepository foodRepository;

    @PostMapping(value = "/korzinka")
    public String addKorzinka(@RequestBody KorzinkaFoodDTO korzinkaFoodDTO){
        Korzinka korzinka = new Korzinka();
        korzinka.setAllPrice(korzinkaFoodDTO.getAllPrice());
        Optional<Food> optionalFood = foodRepository.findById(korzinkaFoodDTO.getFood_id());
        if (optionalFood.isPresent()){
            korzinka.setFood(optionalFood.get());
            korzinkaRepository.save(korzinka);
            return "Save Korzinka";
        }else {
            return "Not Add";
        }
    }

    @GetMapping(value = "/korzinka")
    public List<Korzinka> getAllKorzinka(){
        return korzinkaRepository.findAll();
    }

    @GetMapping(value = "/korzinka/{food_id}")
    public List<Korzinka> getKorzinka(@PathVariable Integer food_id){
        List<Korzinka> allByFoodId = korzinkaRepository.findAllByFoodId(food_id);
        return allByFoodId;
    }

    @DeleteMapping(value = "/korzinka/{id}")
    public String deleteKorzinka(@RequestBody Korzinka korzinka, @PathVariable Integer id){
        korzinkaRepository.deleteById(id);
        return "Delete Korzinka";
    }

    @PutMapping(value = "/korzinka/{id}")
    public String editKorzinka(@RequestBody KorzinkaFoodDTO korzinkaFoodDTO, @PathVariable Integer id){
        Optional<Korzinka> optionalKorzinka = korzinkaRepository.findById(id);
        if (optionalKorzinka.isPresent()){
            Korzinka korzinka = optionalKorzinka.get();
            korzinka.setAllPrice(korzinkaFoodDTO.getAllPrice());
            korzinkaRepository.save(korzinka);
        }
        return "Edit Korzinka";
    }
}
