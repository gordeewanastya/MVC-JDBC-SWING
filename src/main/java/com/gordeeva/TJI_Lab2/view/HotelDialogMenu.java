package com.gordeeva.TJI_Lab2.view;

import com.gordeeva.TJI_Lab2.controller.HotelController;
import com.gordeeva.TJI_Lab2.model.Hotel;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class HotelDialogMenu {

    HotelController hotelController;

    public  void openAddHotelDialog(JFrame jFrame) {
        JDialog addHotelDialog = new JDialog(jFrame, "Add Hotel", true);
        addHotelDialog.setSize(300, 300);
        addHotelDialog.setLocationRelativeTo(jFrame);

        JPanel panel = new JPanel();
        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField(20);
        JLabel addressLabel = new JLabel("Address: ");
        JTextField addressField = new JTextField(20);
        JLabel ratingLabel = new JLabel("Star Rating: ");
        JTextField ratingField = new JTextField(20);
        JLabel hotelAddedField = new JLabel("A new hotel has been added!");
        JLabel hotelNotAddedField = new JLabel("A new hotel has not been added!");

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                String rating = ratingField.getText();

                Hotel hotel = new Hotel();
                hotel.setName(name);
                hotel.setAddress(address);
                hotel.setStarRating(Integer.valueOf(rating));
                Long hotelId = hotelController.createHotel(hotel);

//                if(hotelId != 0L){
//                    panel.removeAll(); // Очистить панель от предыдущего содержимого
//                    panel.add(hotelAddedField);
//                }else{
//                    panel.removeAll(); // Очистить панель от предыдущего содержимого
//                    panel.add(hotelNotAddedField);
//                }
//
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
                addHotelDialog.dispose();
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(ratingLabel);
        panel.add(ratingField);
        panel.add(addButton);


        addHotelDialog.add(panel);
        addHotelDialog.setVisible(true);
    }

    public void openAllHotelsDialog(JFrame jFrame) {
        AllHotelsTableModel hotelsTableModel = new AllHotelsTableModel();
        JTable table = new JTable(hotelsTableModel);

        JPanel panel = new JPanel();
        JDialog addHotelDialog = new JDialog(jFrame, "All Hotels", true);
        addHotelDialog.setSize(600, 600);
        addHotelDialog.setLocationRelativeTo(jFrame);

        panel.add(new JScrollPane(table));

        addHotelDialog.add(panel);
        addHotelDialog.setVisible(true);
    }
}
