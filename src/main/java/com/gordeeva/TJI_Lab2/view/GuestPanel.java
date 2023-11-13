package com.gordeeva.TJI_Lab2.view;

import com.gordeeva.TJI_Lab2.controller.GuestController;
import com.gordeeva.TJI_Lab2.model.Guest;
import com.gordeeva.TJI_Lab2.model.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.util.List;

public class GuestPanel extends JPanel {
    JLabel firstNameLabel, lastNameLabel, emailLabel,phoneLabel;
    JTextField firstNameTextField, lastNameTextField, emailTextField, phoneTextField;
    JButton saveBtn,editBtn,deleteBtn,displayBtn;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scroll;
    public JComboBox comboBoxList;

    GuestController controller;

    public GuestPanel(GuestController controller) throws SQLException {
        this.setPreferredSize(new Dimension(800,700));
        firstNameLabel = new JLabel("FirstName: ");
        firstNameLabel.setBounds(10, 10, 100, 30);
        this.add(firstNameLabel);
        firstNameTextField = new JTextField(15);
        firstNameTextField.setBounds(120, 10, 150, 30);
        this.add(firstNameTextField);

        lastNameLabel = new JLabel("LastName: ");
        lastNameLabel.setBounds(10, 50, 100, 30);
        this.add(lastNameLabel);
        lastNameTextField = new JTextField(15);
        lastNameTextField.setBounds(120, 50, 150, 30);
        this.add(lastNameTextField);

        emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(10, 90, 100, 30);
        this.add(emailLabel);
        emailTextField = new JTextField(12);
        emailTextField.setBounds(120, 90, 150, 30);
        this.add(emailTextField);

        phoneLabel = new JLabel("Phone: ");
        phoneLabel.setBounds(10,120,100,30);
        this.add(phoneLabel);
        phoneTextField = new JTextField(15);
        phoneTextField.setBounds(120,130,150,30);
        this.add(phoneTextField);

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

        tableModel = new DefaultTableModel();
        String col[] = {"Id","FirstName","LastName", "Email", "Phone"};
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
        saveBtn.addActionListener(event -> saveGuest());
        editBtn.addActionListener(event -> editGuest());
        deleteBtn.addActionListener(event -> deleteGuest());
        displayBtn.addActionListener(event -> displayGuest());

        comboBoxList.addItemListener(itemEvent -> comboBoxState(itemEvent));
    }

    private void loadId(){
        comboBoxList.removeAllItems();
        comboBoxList.addItem("Select ID");
        java.util.List<Long> guestsIdList = controller.getGuestsIdList();
        guestsIdList.stream().forEach(id -> comboBoxList.addItem(Long.toString(id)));
    }

    private void loadData(){
        tableModel.setRowCount(0);
        List<Guest> allGuests = controller.getAllGuests();
        allGuests.stream().forEach(guest -> {
            tableModel.addRow(new Object[] {guest.getId(),guest.getFirstName(), guest.getLastName(), guest.getEmail(), guest.getPhoneNumber()});
        });
    }

    private void showResultMesage(int resultOfExecution, String operation){
        if(resultOfExecution == 1){
            JOptionPane.showMessageDialog(null, "Row " + operation + " successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No Row " + operation +".");
        }
    }

    private void saveGuest() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();

        Guest guestToSave = new Guest();
        guestToSave.setFirstName(firstName);
        guestToSave.setLastName(lastName);
        guestToSave.setEmail(email);
        guestToSave.setPhoneNumber(phone);

        try{
            showResultMesage(controller.addGuest(guestToSave), "inserted");
            loadData();
            loadId();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void editGuest() {
        Long guestId = Long.parseLong((String)comboBoxList.getSelectedItem());
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();

        Guest guestToUpdate = controller.getGuestById(guestId).orElse(new Guest());
        guestToUpdate.setFirstName(firstName);
        guestToUpdate.setLastName(lastName);
        guestToUpdate.setEmail(email);
        guestToUpdate.setPhoneNumber(phone);
        try{
            showResultMesage(controller.updateGuest(guestToUpdate), "updated");
            loadData();
            loadId();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void deleteGuest() {
        Long guestId = Long.parseLong((String)comboBoxList.getSelectedItem());
        try{
            showResultMesage(controller.deleteGuest(guestId), "deleted");
            loadData();
            loadId();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void displayGuest() {
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
                Long guestId = Long.parseLong((String)comboBoxList.getSelectedItem());
                Guest guest;
                try{
                    guest = controller.getGuestById(guestId).orElse(new Guest());
                    tableModel.setRowCount(0);
                    tableModel.addRow(new Object[]{guest.getId(),guest.getFirstName(), guest.getLastName(), guest.getEmail(), guest.getPhoneNumber()});
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

}
