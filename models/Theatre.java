package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
public class Theatre extends BaseModel{
    private String name;

    @OneToMany
    private List <Screen> screens;

    @ManyToOne
    private Region region;


}
