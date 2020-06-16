package com.kukhta.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class User {
    private int id;
    private String email;
    private String password;
    private int carId;
    private String name;
    private String surname;
    private UserRole role;
}
