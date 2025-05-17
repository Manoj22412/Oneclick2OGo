package com.manoj.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;
    private String description;
    private  Long price;

    @ManyToOne
    private  Category carCategory;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private boolean available;

    @ManyToOne
    private CarD carD;

    private boolean isEv;
    private boolean isPetrol;
    private boolean isSports;
    private boolean isDiesel;

    @ManyToMany
    private List<FeaturesItem> features=new ArrayList<>();

    private Date creationDate;

}
