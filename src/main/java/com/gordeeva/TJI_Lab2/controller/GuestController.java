package com.gordeeva.TJI_Lab2.controller;

import com.gordeeva.TJI_Lab2.model.Guest;
import com.gordeeva.TJI_Lab2.service.GuestService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class GuestController {
    private GuestService guestService;

    public int addGuest(Guest guest){
        return guestService.addGuest(guest);
    }

    public Optional<Guest> getGuestById(Long guestId){
        return guestService.getGuestById(guestId);
    }

    public List<Guest> getAllGuests(){
        return guestService.getAllGuests();
    }

    public List<Long> getGuestsIdList(){ return guestService.getGuestsIdList();}

    public int updateGuest(Guest guest){
        return guestService.updateGuest(guest);
    }

    public int deleteGuest(Long guestId){
        return guestService.deleteGuest(guestId);
    }
}
