package com.gordeeva.TJI_Lab2.view;

import com.gordeeva.TJI_Lab2.model.Hotel;
import com.gordeeva.TJI_Lab2.service.HotelService;
import org.springframework.stereotype.Component;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

@Component
public class AllHotelsTableModel extends DefaultTableModel {
    private final String[] columnNames = {"hotel_id", "name", "address", "star_rating"};
    private final HotelService hotelService = new HotelService();
    private  List<Hotel> allHotels = new ArrayList<>();

    public AllHotelsTableModel(){
        allHotels = hotelService.getAllHotels();
        addColumnNames();
        addData();
    }

    public void addColumnNames() {
        for (String columnName : columnNames) {
            super.addColumn(columnName);
        }
    }

    public void addData() {
        for (Hotel hotel : allHotels) {
            Object[] hotelData = {
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getAddress(),
                    hotel.getStarRating()
            };
            super.addRow(hotelData);
        }
    }

    public void addProductDataToRow(Hotel hotel) {
        Object[] hotelData = {
                hotel.getId(),
                hotel.getName(),
                hotel.getAddress(),
                hotel.getStarRating()
        };
        super.addRow(hotelData);
    }

    public void updateProductDataInRow(Hotel hotel) {
        Object[] hotelData = {
                hotel.getId(),
                hotel.getName(),
                hotel.getAddress(),
                hotel.getStarRating()
        };
        for (int i = 0; i < getRowCount(); i++) {
            if (hotel.getId().equals((int) getValueAt(i, 0))) {
                for (int j = 0; j < hotelData.length; j++) {
                    super.setValueAt(hotelData[j], i, j+2);
                }
            }
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 1;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 1:
                return Boolean.class;
            default:
                return String.class;
        }
    }

}
