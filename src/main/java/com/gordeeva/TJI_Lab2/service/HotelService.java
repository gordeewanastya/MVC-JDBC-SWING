package com.gordeeva.TJI_Lab2.service;

import com.gordeeva.TJI_Lab2.dao.impl.HotelDaoImpl;
import com.gordeeva.TJI_Lab2.model.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();

    public Long createHotel(Hotel hotel){
        return hotelDaoImpl.createHotel(hotel);
    }

    public Optional<Hotel> getHotelById(Long hotelId){
        return hotelDaoImpl.getHotelById(hotelId);
    }

    public List<Hotel> getAllHotels(){
        return hotelDaoImpl.getAllHotels();
    }

    public List<Long> getHotelsIdList(){ return hotelDaoImpl.getHotelsIdList();}

    public void updateHotel(Hotel hotel){
        hotelDaoImpl.updateHotel(hotel);
    }

    public void deleteHotel(Long hotelId){
        hotelDaoImpl.deleteHotel(hotelId);
    }
}
