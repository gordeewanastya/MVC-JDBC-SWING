package com.gordeeva.TJI_Lab2.dao;

import com.gordeeva.TJI_Lab2.model.Guest;

import java.util.List;
import java.util.Optional;

public interface GuestDao {
        int addGuest(Guest guest);
        Optional<Guest> getGuestById(Long guestId);
        List<Guest> getAllGuests();
        List<Long> getGuestsIdList();
        int updateGuest(Guest guest);
        int deleteGuest(Long guestId);
}
