package com.example.teamproject.repository;

import com.example.teamproject.entity.UserAddress;
import com.example.teamproject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    List<Users> findAllByKorzinkaId(Integer korzinka_id);
}
