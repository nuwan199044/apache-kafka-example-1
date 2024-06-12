package com.myapp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {
    private int id;
    private String name;
    private String email;
    private String contact;
}
