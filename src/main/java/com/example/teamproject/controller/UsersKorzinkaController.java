package com.example.teamproject.controller;

import com.example.teamproject.dto.KorzinkaFoodDTO;
import com.example.teamproject.dto.KorzinkaUsersDTO;;
import com.example.teamproject.entity.Korzinka;
import com.example.teamproject.entity.Users;
import com.example.teamproject.repository.KorzinkaRepository;
import com.example.teamproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UsersKorzinkaController {
    @Autowired
   UsersRepository usersRepository;

    @Autowired
    KorzinkaRepository korzinkaRepository;

    @PostMapping(value = "/customer")
    public String addCustomer(@RequestBody KorzinkaUsersDTO korzinkaUsersDTO){
        Users users = new Users();
        users.setName(korzinkaUsersDTO.getName());
        users.setPhoneNumber(korzinkaUsersDTO.getPhoneNumber());
        Optional<Korzinka> optionalKorzinka = korzinkaRepository.findById(korzinkaUsersDTO.getKorzinka_id());
        if (optionalKorzinka.isPresent()){
            users.getKorzinka(optionalKorzinka.get());
            usersRepository.save(users);
            return "Save Customer";
        }else {
            return "Not Add Customer";
        }
    }

    @GetMapping(value = "/customer")
    public List<Users> getAllCustomer(){
        return usersRepository.findAll();
    }

    @GetMapping(value = "/customer/{korzinka_id}")
    public List<Users> getCustomer(@PathVariable Integer korzinka_id){
        List<Users> allByKorzinkaId = usersRepository.findAllByKorzinkaId(korzinka_id);
        return allByKorzinkaId;
    }

    @DeleteMapping(value = "/customer/{id}")
    public String deleteCustomer(@RequestBody Users users, @PathVariable Integer id){
        usersRepository.deleteById(id);
        return "Delete Customer";
    }

    @PutMapping(value = "/customer/{id}")
    public String editCustomer(@RequestBody KorzinkaUsersDTO korzinkaUsersDTO, @PathVariable Integer id){
        Optional<Users> optionalUsers = usersRepository.findById(id);
        if (optionalUsers.isPresent()){
            Users users = optionalUsers.get();
            users.setName(korzinkaUsersDTO.getName());
            users.setPhoneNumber(korzinkaUsersDTO.getPhoneNumber());
           usersRepository.save(users);
        }
        return "Edit Customer";
    }


}
