package com.gordeeva.TJI_Lab2.view;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class HotelAppView {

    JFrame frame;
    JTabbedPane tabbedPane;
    HotelPanel hotelPanel;
    GuestPanel guestPanel;
    RoomPanel roomPanel;

    public HotelAppView(HotelPanel hotelPanel,GuestPanel guestPanel,RoomPanel roomPanel) throws SQLException {
        frame = new JFrame("Hotel Complex Application");
        frame.setSize(800,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.setBounds(0,650,100,30);

        this.hotelPanel = hotelPanel;
        tabbedPane.addTab("Hotels",hotelPanel);

        this.guestPanel = guestPanel;
        tabbedPane.addTab("Guests", guestPanel);

        this.roomPanel = roomPanel;
        tabbedPane.addTab("Rooms", roomPanel);

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
