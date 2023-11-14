package com.gordeeva.TJI_Lab2.view;

import com.gordeeva.TJI_Lab2.controller.RoomController;
import com.gordeeva.TJI_Lab2.model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.List;

public class RoomPanel extends JPanel{
    JLabel hotelIdLabel, roomNumberLabel, roomTypeLabel, rateLabel;
    JTextField hotelIdTextField, roomNumberTextField, roomTypeTextField, rateTextField;
    JButton saveBtn,editBtn,deleteBtn,displayBtn, businessRoomsBtn;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scroll;
    public JComboBox comboBoxList;
    private RoomController controller;
    public RoomPanel(RoomController controller){
        this.setPreferredSize(new Dimension(800,700));
        hotelIdLabel = new JLabel("HotelId: ");
        hotelIdLabel.setBounds(10, 10, 100, 30);
        this.add(hotelIdLabel);
        hotelIdTextField = new JTextField(15);
        hotelIdTextField.setBounds(120, 10, 150, 30);
        this.add(hotelIdTextField);

        roomNumberLabel = new JLabel("RoomNumber: ");
        roomNumberLabel.setBounds(10, 50, 100, 30);
        this.add(roomNumberLabel);
        roomNumberTextField = new JTextField(15);
        roomNumberTextField.setBounds(120, 50, 150, 30);
        this.add(roomNumberTextField);

        roomTypeLabel = new JLabel("RoomType: ");
        roomTypeLabel.setBounds(10, 90, 100, 30);
        this.add(roomTypeLabel);
        roomTypeTextField = new JTextField(12);
        roomTypeTextField.setBounds(120, 90, 150, 30);
        this.add(roomTypeTextField);

        rateLabel = new JLabel("Rate: ");
        rateLabel.setBounds(10,120,100,30);
        this.add(rateLabel);
        rateTextField = new JTextField(15);
        rateTextField.setBounds(120,130,150,30);
        this.add(rateTextField);

        saveBtn = new JButton("Save");
        saveBtn.setBounds(10, 130, 100,20);
        this.add(saveBtn);

        editBtn = new JButton("Edit");
        editBtn.setBounds(120, 130, 100,20);
        this.add(editBtn);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(10, 170, 100,20);
        this.add(deleteBtn);

        displayBtn = new JButton("Display");
        displayBtn.setBounds(120,170, 100, 20);
        this.add(displayBtn);

        businessRoomsBtn = new JButton("Business Type");
        businessRoomsBtn.setBounds(170, 200, 110, 20);
        this.add(businessRoomsBtn);

        tableModel = new DefaultTableModel();
        String col[] = {"RoomId","HotelId","RoomNumber", "RoomType", "Rate"};
        tableModel.setColumnIdentifiers(col);

        table = new JTable();
        table.setModel(tableModel);
        scroll = new JScrollPane(table);
        scroll.setBounds(350,10,360,300);
        this.add(scroll);

        comboBoxList = new JComboBox();
        comboBoxList.setBounds(10,230,100,20);
        this.add(comboBoxList);

        this.controller = controller;
        loadId();
        loadData();

        this.setVisible(true);

        //Add action listeners to the buttons
        saveBtn.addActionListener(event -> saveRoom());
        editBtn.addActionListener(event -> editRoom());
        deleteBtn.addActionListener(event -> deleteRoom());
        displayBtn.addActionListener(event -> displayRoom());
        businessRoomsBtn.addActionListener(event -> displayBusinessRooms());

        comboBoxList.addItemListener(itemEvent -> comboBoxState(itemEvent));
    }

    private void loadId(){
        comboBoxList.removeAllItems();
        comboBoxList.addItem("Select ID");
        java.util.List<Long> roomsIdList = controller.getRoomsIdList();
        roomsIdList.stream().forEach(id -> comboBoxList.addItem(Long.toString(id)));
    }

    private void loadData(){
        tableModel.setRowCount(0);
        List<Room> allRooms = controller.getAllRooms();
        allRooms.stream().forEach(room -> {
            tableModel.addRow(new Object[] {
                    room.getId(),
                    room.getHotelId(),
                    room.getRoomNumber(),
                    room.getRoomType(),
                    room.getRate()
            });
        });
    }

    private void showResultMesage(int resultOfExecution, String operation){
        if(resultOfExecution == 1){
            JOptionPane.showMessageDialog(null, "Row " + operation + " successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No Row " + operation +".");
        }
    }

    private void saveRoom() {
        Long hotelId = Long.parseLong(hotelIdTextField.getText());
        String roomNumber = roomNumberTextField.getText();
        String roomType = roomTypeTextField.getText();
        Float rate = Float.parseFloat(rateTextField.getText());

        Room roomToSave = new Room();
        roomToSave.setHotelId(hotelId);
        roomToSave.setRoomNumber(roomNumber);
        roomToSave.setRoomType(roomType);
        roomToSave.setRate(rate);

        try{
            showResultMesage(controller.addRoom(roomToSave), "inserted");
            loadData();
            loadId();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void editRoom() {
        Long roomId = Long.parseLong((String)comboBoxList.getSelectedItem());
        Long hotelId = Long.parseLong(hotelIdTextField.getText());
        String roomNumber = roomNumberTextField.getText();
        String roomType = roomTypeTextField.getText();
        Float rate = Float.parseFloat(rateTextField.getText());

        Room roomToUpdate = controller.getRoomById(roomId).orElse(new Room());
        roomToUpdate.setHotelId(hotelId);
        roomToUpdate.setRoomNumber(roomNumber);
        roomToUpdate.setRoomType(roomType);
        roomToUpdate.setRate(rate);
        try{
            showResultMesage(controller.updateRoom(roomToUpdate), "updated");
            loadData();
            loadId();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void deleteRoom() {
        Long roomId = Long.parseLong((String)comboBoxList.getSelectedItem());
        try{
            showResultMesage(controller.deleteRoom(roomId), "deleted");
            loadData();
            loadId();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void displayRoom() {
        loadData();
        loadId();
    }

    private void comboBoxState(ItemEvent itemEvent) {
        if(itemEvent.getStateChange()==ItemEvent.SELECTED){
            String selectedItem = comboBoxList.getSelectedItem().toString();
            if(selectedItem.equals("Select ID")){
                try{
                    loadData();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }else{
                Long roomId = Long.parseLong((String)comboBoxList.getSelectedItem());
                Room room;
                try{
                    room = controller.getRoomById(roomId).orElse(new Room());
                    tableModel.setRowCount(0);
                    tableModel.addRow(new Object[]{
                            room.getId(),
                            room.getHotelId(),
                            room.getRoomNumber(),
                            room.getRoomType(),
                            room.getRate()
                    });
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    private void displayBusinessRooms() {
        tableModel.setRowCount(0);
        List<Room> allBusinessRooms = controller.getBusinessRooms();
        allBusinessRooms.stream().forEach(room -> {
            tableModel.addRow(new Object[] {
                    room.getId(),
                    room.getHotelId(),
                    room.getRoomNumber(),
                    room.getRoomType(),
                    room.getRate()
            });
        });
    }

}
