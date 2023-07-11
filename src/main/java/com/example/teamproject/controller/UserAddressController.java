package com.example.teamproject.controller;

import com.example.teamproject.dto.KorzinkaUsersDTO;
import com.example.teamproject.dto.UsersUserAddressDTO;
import com.example.teamproject.entity.Address;
import com.example.teamproject.entity.UserAddress;
import com.example.teamproject.entity.Users;
import com.example.teamproject.repository.UserAddressRepository;
import com.example.teamproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserAddressController {
    @Autowired
    UserAddressRepository userAddressRepository;

    @Autowired
    UsersRepository usersRepository;

    @PostMapping(value = "/address")
    public String addAddress(@RequestBody UsersUserAddressDTO usersUserAddressDTO){
        UserAddress userAddress = new UserAddress();
        userAddress.setDistrict(usersUserAddressDTO.getDistrict());
        userAddress.setStreet(usersUserAddressDTO.getStreet());
        userAddress.setHomeNumber(usersUserAddressDTO.getHomeNumber());
        Optional<Users> optionalUsers = usersRepository.findById(usersUserAddressDTO.getUsers_id());
        if (optionalUsers.isPresent()){
            userAddress.setUsers(optionalUsers.get());
            userAddressRepository.save(userAddress);
            return "Save UserAddress";
        }else {
            return "Not Add UserAddress";
        }
    }

    @GetMapping(value = "/address")
    public List<UserAddress> getAllUserAddress(){
        return userAddressRepository.findAll();
    }

    @GetMapping(value = "/address/{users_id}")
    public List<UserAddress> getUserAddress(@PathVariable Integer users_id){
        List<UserAddress> allByUsersId = userAddressRepository.findAllByUsersId(users_id);
        return allByUsersId;
    }

    @DeleteMapping(value = "/address/{id}")
    public String deleteUserAddress(@RequestBody UserAddress userAddress, @PathVariable Integer id){
        userAddressRepository.deleteById(id);
        return "Delete UserAddress";
    }

    @PutMapping(value = "/address/{users_id}")
    public String editUserAddress(@RequestBody UsersUserAddressDTO usersUserAddressDTO, @PathVariable Integer users_id){
        Optional<UserAddress> optionalUserAddress = userAddressRepository.findById(users_id);
        if (optionalUserAddress.isPresent()){
            UserAddress userAddress = optionalUserAddress.get();
            userAddress.setDistrict(usersUserAddressDTO.getDistrict());
            userAddress.setStreet(usersUserAddressDTO.getStreet());
            userAddress.setHomeNumber(usersUserAddressDTO.getHomeNumber());
            userAddressRepository.save(userAddress);
        }
        return "Edit UserAddress";
    }
}
