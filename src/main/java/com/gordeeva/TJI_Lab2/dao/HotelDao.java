package com.gordeeva.TJI_Lab2.dao;

import com.gordeeva.TJI_Lab2.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelDao {
     int createHotel(Hotel hotel);
     Optional<Hotel> getHotelById(Long hotelId);
     List<Hotel> getAllHotels();
     List<Long> getHotelsIdList();
     int updateHotel(Hotel hotel);
     int deleteHotel(Long hotelId);
}

