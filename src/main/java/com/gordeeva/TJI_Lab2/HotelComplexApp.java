package com.gordeeva.TJI_Lab2;

import com.gordeeva.TJI_Lab2.controller.HotelController;
import com.gordeeva.TJI_Lab2.service.HotelService;
import com.gordeeva.TJI_Lab2.view.HotelComplexGUI;
import com.gordeeva.TJI_Lab2.view.HotelView;

import java.sql.SQLException;

public class HotelComplexApp {
    public static void main(String[] args) throws SQLException {
        new HotelView(new HotelController(new HotelService()));
    }
}
