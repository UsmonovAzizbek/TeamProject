package com.example.teamproject.entity;

import com.example.teamproject.dto.KorzinkaUsersDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String phoneNumber;
    @OneToOne
    private Korzinka korzinka;

    public void getKorzinka(Korzinka korzinka) {

    }
}
