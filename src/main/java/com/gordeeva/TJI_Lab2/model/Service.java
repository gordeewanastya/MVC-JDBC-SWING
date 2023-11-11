package com.gordeeva.TJI_Lab2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    private Long id;
    private String name;
    private String description;
    private Float price;
}
