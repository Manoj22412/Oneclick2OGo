package com.manoj.dto;

import com.manoj.model.CarD;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class CarDDto extends CarD {

    private String title;

    @Column(length = 1000)
    private List<String> images;

    private String description;
    private Long id;

}
