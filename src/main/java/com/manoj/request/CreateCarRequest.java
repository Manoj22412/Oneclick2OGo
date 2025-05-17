package com.manoj.request;

import com.manoj.model.FeaturesItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateCarRequest {
    private String name;
    private String description;
    private String price;

    private String category;
    private List<String>images;

    private Long CarDId;
    private boolean eV;
    private boolean petrol;
    private boolean diesel;
    private boolean sportCar;
    private List<FeaturesItem> features;
}
