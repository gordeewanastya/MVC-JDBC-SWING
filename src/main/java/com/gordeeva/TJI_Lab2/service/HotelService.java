package com.gordeeva.TJI_Lab2.service;

import com.gordeeva.TJI_Lab2.dao.impl.HotelDaoImpl;
import com.gordeeva.TJI_Lab2.model.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HotelService {
    private final HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();

    public int createHotel(Hotel hotel){
        return hotelDaoImpl.createHotel(hotel);
    }

    public Optional<Hotel> getHotelById(Long hotelId){
        return hotelDaoImpl.getHotelById(hotelId);
    }

    public List<Hotel> getAllHotels(){
        return hotelDaoImpl.getAllHotels();
    }

    public List<Long> getHotelsIdList(){ return hotelDaoImpl.getHotelsIdList();}

    public int updateHotel(Hotel hotel){
        return hotelDaoImpl.updateHotel(hotel);
    }

    public int deleteHotel(Long hotelId){
        return hotelDaoImpl.deleteHotel(hotelId);
    }

    public List<Hotel> getHotelsWithHighRating() {return hotelDaoImpl.getHotelsWithHighRating();}
}
