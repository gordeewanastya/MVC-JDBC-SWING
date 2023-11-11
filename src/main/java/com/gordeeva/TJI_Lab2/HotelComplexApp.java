package com.gordeeva.TJI_Lab2;

import com.gordeeva.TJI_Lab2.controller.HotelController;
import com.gordeeva.TJI_Lab2.service.HotelService;
import com.gordeeva.TJI_Lab2.view.HotelComplexGUI;

public class HotelComplexApp {
    public static void main(String[] args) {
        HotelController controller = new HotelController(new HotelService());
        HotelComplexGUI hotelComplexGUI = new HotelComplexGUI(controller);
        hotelComplexGUI.createGUI();
    }
}
