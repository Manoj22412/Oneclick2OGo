package com.manoj.request;

import com.manoj.model.Address;
import com.manoj.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateCarDRequest {

    private Long id;
    private String name;
    private String description;
    private Address address;
    private ContactInformation contactInformation;
    private String carType;
    private String openingHours;
    private List<String> images;
}
