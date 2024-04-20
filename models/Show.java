package com.example.bookmyshow.models;

import com.example.bookmyshow.models.enums.Feature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter

@Entity(name = "Shows")
public class Show extends BaseModel{
    @ManyToOne
    private Screen screen;

    @ManyToOne
    private Movie movie;

    private Date startTime;
    private Date endTime;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
}
