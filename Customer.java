package com.example.ex_006_draxler.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String address;
    private int id;
    private String name;
    private float sales;
}
