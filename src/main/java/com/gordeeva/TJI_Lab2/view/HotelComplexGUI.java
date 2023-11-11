package com.gordeeva.TJI_Lab2.view;

import com.gordeeva.TJI_Lab2.controller.HotelController;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class HotelComplexGUI{
    HotelController controller;
    HotelDialogMenu hotelDialogMenu;
    JFrame jFrame;
    JPanel mainPanel;
    JMenuItem createHotelMenuItem;
    JMenuItem getHotelByIdMenuItem;
    JMenuItem getAllHotelsMenuItem;
    JMenuItem updateHotelMenuItem;
    JMenuItem deleteHotelMenuItem;

    JMenuItem createGuestMenuItem;
    JMenuItem getGuestByIdMenuItem;
    JMenuItem getAllGuestsMenuItem;
    JMenuItem updateGuestMenuItem;
    JMenuItem deleteGuestMenuItem;

    public HotelComplexGUI(HotelController controller){
        this.controller = controller;
        this.hotelDialogMenu = new HotelDialogMenu(controller);
    }

    public void createGUI(){
        jFrame = new JFrame();
        jFrame.setTitle("Hotel Complex App");
        jFrame.setSize(800, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createMenuBar();
        addGreetingMessage();

        jFrame.setVisible(true);
    }

    private void addGreetingMessage() {
        mainPanel = new JPanel(new BorderLayout());
        Font customFont = new Font("Arial", Font.BOLD, 50);
        JLabel label = new JLabel("Welcome to the Hotel Complex application!!!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        mainPanel.add(label, BorderLayout.CENTER);
        jFrame.getContentPane().add(mainPanel);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu hotelsMenu = new JMenu("Hotels");
        createHotelMenuItem = new JMenuItem("Add hotel");
        getHotelByIdMenuItem = new JMenuItem("Get hotel by id");
        getAllHotelsMenuItem = new JMenuItem("Get all hotels");
        updateHotelMenuItem = new JMenuItem("Update hotel");
        deleteHotelMenuItem = new JMenuItem("Delete hotel");
        hotelsMenu.add(createHotelMenuItem);
        hotelsMenu.add(getHotelByIdMenuItem);
        hotelsMenu.add(getAllHotelsMenuItem);
        hotelsMenu.add(updateHotelMenuItem);
        hotelsMenu.add(deleteHotelMenuItem);

        //Adding action listeners to the menu items:
        createHotelMenuItem.addActionListener(event -> hotelDialogMenu.openAddHotelDialog(jFrame));
        getAllHotelsMenuItem.addActionListener(event -> hotelDialogMenu.openAllHotelsDialog(jFrame));

        JMenu guestMenu = new JMenu("Guests");
        createGuestMenuItem = new JMenuItem("Add guest");
        getGuestByIdMenuItem = new JMenuItem("Get guest by id");
        getAllGuestsMenuItem = new JMenuItem("Get all guests");
        updateGuestMenuItem = new JMenuItem("Update guest");
        deleteGuestMenuItem = new JMenuItem("Delete guest");
        guestMenu.add(createGuestMenuItem);
        guestMenu.add(getGuestByIdMenuItem);
        guestMenu.add(getAllGuestsMenuItem);
        guestMenu.add(updateGuestMenuItem);
        guestMenu.add(deleteGuestMenuItem);


        menuBar.add(hotelsMenu);
        menuBar.add(guestMenu);
        jFrame.setJMenuBar(menuBar);
    }


}
