package com.gordeeva.TJI_Lab2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    private Long id;
    private String name;
    private String address;
    private Integer starRating;
}
