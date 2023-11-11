package com.gordeeva.TJI_Lab2.controller;

import com.gordeeva.TJI_Lab2.model.Hotel;
import com.gordeeva.TJI_Lab2.service.HotelService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HotelController {
    private HotelService hotelService;

    public Long createHotel(Hotel hotel){
        return hotelService.createHotel(hotel);
    }
}
