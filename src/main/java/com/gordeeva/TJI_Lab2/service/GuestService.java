package com.gordeeva.TJI_Lab2.service;

import com.gordeeva.TJI_Lab2.dao.impl.GuestDaoImpl;
import com.gordeeva.TJI_Lab2.model.Guest;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class GuestService {
    private final GuestDaoImpl guestDaoImpl = new GuestDaoImpl();

    public int addGuest(Guest guest){
        return guestDaoImpl.addGuest(guest);
    }

    public Optional<Guest> getGuestById(Long guestId){
        return guestDaoImpl.getGuestById(guestId);
    }

    public List<Guest> getAllGuests(){
        return guestDaoImpl.getAllGuests();
    }

    public List<Long> getGuestsIdList(){ return guestDaoImpl.getGuestsIdList();}

    public int updateGuest(Guest guest){
        return guestDaoImpl.updateGuest(guest);
    }

    public int deleteGuest(Long guestId){
        return guestDaoImpl.deleteGuest(guestId);
    }
}
