package com.gordeeva.TJI_Lab2;

import com.gordeeva.TJI_Lab2.controller.GuestController;
import com.gordeeva.TJI_Lab2.controller.HotelController;
import com.gordeeva.TJI_Lab2.service.GuestService;
import com.gordeeva.TJI_Lab2.service.HotelService;
import com.gordeeva.TJI_Lab2.view.GuestPanel;
import com.gordeeva.TJI_Lab2.view.HotelAppView;
import com.gordeeva.TJI_Lab2.view.HotelPanel;

import java.sql.SQLException;

public class HotelComplexApp {
    public static void main(String[] args) throws SQLException {
//        new HotelView(new HotelController(new HotelService()));
        new HotelAppView(
                new HotelPanel(new HotelController(new HotelService())),
                new GuestPanel(new GuestController(new GuestService()))
        );
    }

}
