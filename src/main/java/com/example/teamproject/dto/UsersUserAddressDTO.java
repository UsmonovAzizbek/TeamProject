package com.example.teamproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersUserAddressDTO {
    private String district;
    private String street;
    private String homeNumber;
    private Integer users_id;
}
