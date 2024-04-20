package com.example.bookmyshow.models;

import cucumber.api.java.eo.Se;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Movie extends BaseModel{
    private String name;
    private String description;
}
