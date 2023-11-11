package com.gordeeva.TJI_Lab2.view;

import com.gordeeva.TJI_Lab2.controller.HotelController;
import com.gordeeva.TJI_Lab2.model.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class HotelView {
    JFrame frame;
    JLabel nameLabel, addressLabel,starRatingLabel;
    JTextField nameTextField,addressTextField, starRatingTextField;
    JButton saveBtn,editBtn,deleteBtn,displayBtn;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scroll;
    public JComboBox list;
    HotelController controller;

    public HotelView(HotelController controller) throws SQLException{
        frame = new JFrame("Hotel Table");
        frame.setSize(800,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(10, 10, 100, 30);
        frame.add(nameLabel);
        nameTextField = new JTextField(15);
        nameTextField.setBounds(120, 10, 150, 30);
        frame.add(nameTextField);

        addressLabel = new JLabel("Address: ");
        addressLabel.setBounds(10, 50, 100, 30);
        frame.add(addressLabel);
        addressTextField = new JTextField(15);
        addressTextField.setBounds(120, 50, 150, 30);
        frame.add(addressTextField);

        starRatingLabel = new JLabel("Star Rating: ");
        starRatingLabel.setBounds(10, 90, 100, 30);
        frame.add(starRatingLabel);
        starRatingTextField = new JTextField(12);
        starRatingTextField.setBounds(120, 90, 150, 30);
        frame.add(starRatingTextField);

        saveBtn = new JButton("Save");
        saveBtn.setBounds(10, 130, 100,20);
        frame.add(saveBtn);

        editBtn = new JButton("Edit");
        editBtn.setBounds(120, 130, 100,20);
        frame.add(editBtn);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(10, 170, 100,20);
        frame.add(deleteBtn);

        displayBtn = new JButton("Display");
        displayBtn.setBounds(120,170, 100, 20);
        frame.add(displayBtn);

        tableModel = new DefaultTableModel();
        String col[] = {"Name", "Address", "Star Rating"};
        tableModel.setColumnIdentifiers(col);

        table = new JTable();
        table.setModel(tableModel);
        scroll = new JScrollPane(table);
        scroll.setBounds(350,10,350,300);
        frame.add(scroll);

        list = new JComboBox();
        list.setBounds(10,230,100,20);
        frame.add(list);

        this.controller = controller;
        loadId();
        loadData();

        frame.setVisible(true);
    }

    public void loadId(){
        list.removeAllItems();
        list.addItem("Select ID");
        List<Long> hotelsIdList = controller.getHotelsIdList();
        hotelsIdList.stream().forEach(id -> list.addItem(Long.toString(id)));
    }

    public void loadData(){
        tableModel.setRowCount(0);
        List<Hotel> allHotels = controller.getAllHotelsList();
        allHotels.stream().forEach(hotel -> {
            tableModel.addRow(new Object[] {hotel.getName(), hotel.getAddress(), hotel.getStarRating()});
        });
    }
}
