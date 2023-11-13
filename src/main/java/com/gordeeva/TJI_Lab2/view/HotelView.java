//package com.gordeeva.TJI_Lab2.view;
//
//import com.gordeeva.TJI_Lab2.controller.HotelController;
//import com.gordeeva.TJI_Lab2.model.Hotel;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.event.ItemEvent;
//import java.sql.SQLException;
//import java.util.List;
//
//public class HotelView {
//    JFrame frame;
//    JLabel nameLabel, addressLabel,starRatingLabel;
//    JTextField nameTextField,addressTextField, starRatingTextField;
//    JButton saveBtn,editBtn,deleteBtn,displayBtn;
//    DefaultTableModel tableModel;
//    JTable table;
//    JScrollPane scroll;
//    public JComboBox comboBoxList;
//    JTabbedPane tabbedPane;
//    HotelController controller;
//
//    public HotelView(HotelController controller) throws SQLException{
//        frame = new JFrame("Hotel Table");
//        frame.setSize(800,700);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(null);
//
//        tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
//        tabbedPane.setBounds(0,650,100,30);
//
//        JPanel panel = new JPanel();
//        panel.add(new JLabel("panel"));
//        tabbedPane.addTab("Tab 1", null, panel, "Hotel Panel 1");
//        frame.add(tabbedPane);
//
//        nameLabel = new JLabel("Name: ");
//        nameLabel.setBounds(10, 10, 100, 30);
//        frame.add(nameLabel);
//        nameTextField = new JTextField(15);
//        nameTextField.setBounds(120, 10, 150, 30);
//        frame.add(nameTextField);
//
//        addressLabel = new JLabel("Address: ");
//        addressLabel.setBounds(10, 50, 100, 30);
//        frame.add(addressLabel);
//        addressTextField = new JTextField(15);
//        addressTextField.setBounds(120, 50, 150, 30);
//        frame.add(addressTextField);
//
//        starRatingLabel = new JLabel("Star Rating: ");
//        starRatingLabel.setBounds(10, 90, 100, 30);
//        frame.add(starRatingLabel);
//        starRatingTextField = new JTextField(12);
//        starRatingTextField.setBounds(120, 90, 150, 30);
//        frame.add(starRatingTextField);
//
//        saveBtn = new JButton("Save");
//        saveBtn.setBounds(10, 130, 100,20);
//        frame.add(saveBtn);
//
//        editBtn = new JButton("Edit");
//        editBtn.setBounds(120, 130, 100,20);
//        frame.add(editBtn);
//
//        deleteBtn = new JButton("Delete");
//        deleteBtn.setBounds(10, 170, 100,20);
//        frame.add(deleteBtn);
//
//        displayBtn = new JButton("Display");
//        displayBtn.setBounds(120,170, 100, 20);
//        frame.add(displayBtn);
//
//        tableModel = new DefaultTableModel();
//        String col[] = {"Id","Name", "Address", "Star Rating"};
//        tableModel.setColumnIdentifiers(col);
//
//        table = new JTable();
//        table.setModel(tableModel);
//        scroll = new JScrollPane(table);
//        scroll.setBounds(350,10,360,300);
//        frame.add(scroll);
//
//        comboBoxList = new JComboBox();
//        comboBoxList.setBounds(10,230,100,20);
//        frame.add(comboBoxList);
//
//        this.controller = controller;
//        loadId();
//        loadData();
//
//        frame.setVisible(true);
//
//        //Add action listeners to the buttons
//        saveBtn.addActionListener(event -> saveHotel());
//        editBtn.addActionListener(event -> editHotel());
//        deleteBtn.addActionListener(event -> deleteHotel());
//        displayBtn.addActionListener(event -> displayHotel());
//
//        comboBoxList.addItemListener(itemEvent -> comboBoxState(itemEvent));
//    }
//
//    private void loadId(){
//        comboBoxList.removeAllItems();
//        comboBoxList.addItem("Select ID");
//        List<Long> hotelsIdList = controller.getHotelsIdList();
//        hotelsIdList.stream().forEach(id -> comboBoxList.addItem(Long.toString(id)));
//    }
//
//    private void loadData(){
//        tableModel.setRowCount(0);
//        List<Hotel> allHotels = controller.getAllHotelsList();
//        allHotels.stream().forEach(hotel -> {
//            tableModel.addRow(new Object[] {hotel.getId(),hotel.getName(), hotel.getAddress(), hotel.getStarRating()});
//        });
//    }
//
//    private void showResultMesage(int resultOfExecution, String operation){
//        if(resultOfExecution == 1){
//            JOptionPane.showMessageDialog(null, "Row " + operation + " successfully.");
//        } else {
//            JOptionPane.showMessageDialog(null, "No Row " + operation +".");
//        }
//    }
//
//    private void saveHotel() {
//        String name = nameTextField.getText();
//        String address = addressTextField.getText();
//        Integer starRating = Integer.valueOf(starRatingTextField.getText());
//
//        Hotel hotelToSave = new Hotel();
//        hotelToSave.setName(name);
//        hotelToSave.setAddress(address);
//        hotelToSave.setStarRating(starRating);
//
//        try{
//            showResultMesage(controller.createHotel(hotelToSave), "inserted");
//            loadData();
//            loadId();
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    private void editHotel() {
//        Long hotelId = Long.parseLong((String)comboBoxList.getSelectedItem());
//        String name = nameTextField.getText();
//        String address = addressTextField.getText();
//        Integer starRating = Integer.valueOf(starRatingTextField.getText());
//
//        Hotel hotelToUpdate = controller.getHotelById(hotelId).orElse(new Hotel());
//        hotelToUpdate.setName(name);
//        hotelToUpdate.setAddress(address);
//        hotelToUpdate.setStarRating(starRating);
//        try{
//            showResultMesage(controller.updateHotel(hotelToUpdate), "updated");
//            loadData();
//            loadId();
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    private void deleteHotel() {
//        Long hotelId = Long.parseLong((String)comboBoxList.getSelectedItem());
//        try{
//            showResultMesage(controller.deleteHotel(hotelId), "deleted");
//            loadData();
//            loadId();
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    private void displayHotel() {
//        loadData();
//        loadId();
//    }
//
//    private void comboBoxState(ItemEvent itemEvent) {
//        if(itemEvent.getStateChange()==ItemEvent.SELECTED){
//            String selectedItem = comboBoxList.getSelectedItem().toString();
//            if(selectedItem.equals("Select ID")){
//                try{
//                    loadData();
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }
//            }else{
//                Long hotelId = Long.parseLong((String)comboBoxList.getSelectedItem());
//                Hotel hotel;
//                try{
//                    hotel = controller.getHotelById(hotelId).orElse(new Hotel());
//                    tableModel.setRowCount(0);
//                    tableModel.addRow(new Object[]{hotel.getId(),hotel.getName(), hotel.getAddress(), hotel.getStarRating()});
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }
//
//}
