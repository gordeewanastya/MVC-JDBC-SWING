package com.gordeeva.TJI_Lab2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private Long id;
    private Long hotelId;
    private String roomNumber;
    private String roomType;
    private Float rate;
}
