package com.gordeeva.TJI_Lab2.controller;

import com.gordeeva.TJI_Lab2.model.Hotel;
import com.gordeeva.TJI_Lab2.service.HotelService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class HotelController {
    private HotelService hotelService;

    public int createHotel(Hotel hotel){
        return hotelService.createHotel(hotel);
    }

    public List<Long> getHotelsIdList(){
        return hotelService.getHotelsIdList();
    }

    public List<Hotel> getAllHotelsList(){
        return hotelService.getAllHotels();
    }

    public Optional<Hotel> getHotelById(Long hotelId){
        return hotelService.getHotelById(hotelId);
    }

    public int updateHotel(Hotel hotel){
        return hotelService.updateHotel(hotel);
    }
}
