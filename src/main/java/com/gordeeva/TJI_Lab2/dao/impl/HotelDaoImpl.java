package com.gordeeva.TJI_Lab2.dao.impl;

import com.gordeeva.TJI_Lab2.config.ConfigurationDBConnection;
import com.gordeeva.TJI_Lab2.dao.HotelDao;
import com.gordeeva.TJI_Lab2.model.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HotelDaoImpl implements HotelDao {
    private final Connection connection = ConfigurationDBConnection.getConnection();
    private final String SQL_CREATE_HOTEL = "INSERT INTO hotels (name,address,star_rating) VALUES (?,?,?)";
    private final String SQL_GET_HOTEL_ID_LIST = "SELECT hotel_id FROM hotels";
    private final String SQL_GET_HOTEL_BY_ID = "SELECT * FROM hotels WHERE hotel_id=?";
    private final String SQL_GET_ALL_HOTELS = "SELECT * FROM hotels";
    private final String SQL_UPDATE_HOTEL = "UPDATE hotels SET name=?, address=?, star_rating=? WHERE hotel_id=?";
    private final String SQL_DELETE_HOTEL = "DELETE FROM hotels WHERE hotel_id=?";
    private final String SQL_GET_HOTELS_WITH_HIGH_RATING = "SELECT * FROM hotels WHERE star_rating > 3";

    @Override
    public int createHotel(Hotel hotel) {
        int resultOfExecution = 0;
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_CREATE_HOTEL, Statement.RETURN_GENERATED_KEYS)){
            prepStatement.setString(1, hotel.getName());
            prepStatement.setString(2, hotel.getAddress());
            prepStatement.setInt(3, hotel.getStarRating());
            resultOfExecution = prepStatement.executeUpdate();
            try (ResultSet generatedKeys = prepStatement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    hotel.setId(generatedKeys.getLong(1));
                }

            }
        } catch (SQLException ex){
            ex.printStackTrace();

        }
        return resultOfExecution;
    }

    @Override
    public Optional<Hotel> getHotelById(Long hotelId) {
        Hotel hotel = new Hotel();
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_GET_HOTEL_BY_ID)){
            prepStatement.setLong(1, hotelId);
            try (ResultSet rs = prepStatement.executeQuery()){
                while (rs.next()){
                    hotel.setId(rs.getLong("hotel_id"));
                    hotel.setName(rs.getString("name"));
                    hotel.setAddress(rs.getString("address"));
                    hotel.setStarRating(rs.getInt("star_rating"));
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return Optional.of(hotel);
    }

    @Override
    public List<Long> getHotelsIdList() {
        List<Long> hotelIdList = new ArrayList<>();
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_GET_HOTEL_ID_LIST)){
            try (ResultSet rs = prepStatement.executeQuery()){
                while (rs.next()){
                    hotelIdList.add(rs.getLong("hotel_id"));
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return hotelIdList;
    }

    @Override
    public List<Hotel> getAllHotels() {
        List<Hotel> allHotels = new ArrayList<>();
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_GET_ALL_HOTELS)){
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getLong(1));
                hotel.setName(rs.getString(2));
                hotel.setAddress(rs.getString(3));
                hotel.setStarRating(rs.getInt(4));
                allHotels.add(hotel);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return allHotels;
    }

    @Override
    public int updateHotel(Hotel hotel) {
        int resultOfExecution = 0;
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_UPDATE_HOTEL)){
            prepStatement.setString(1, hotel.getName());
            prepStatement.setString(2, hotel.getAddress());
            prepStatement.setInt(3, hotel.getStarRating());
            prepStatement.setLong(4, hotel.getId());
            resultOfExecution = prepStatement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return resultOfExecution;
    }

    @Override
    public int deleteHotel(Long hotelId) {
        int resultOfExecution = 0;
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_DELETE_HOTEL)){
            prepStatement.setLong(1, hotelId);
            resultOfExecution = prepStatement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return resultOfExecution;
    }

    @Override
    public List<Hotel> getHotelsWithHighRating() {
        List<Hotel> hotels = new ArrayList<>();
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_GET_HOTELS_WITH_HIGH_RATING)){
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getLong(1));
                hotel.setName(rs.getString(2));
                hotel.setAddress(rs.getString(3));
                hotel.setStarRating(rs.getInt(4));
                hotels.add(hotel);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return hotels;
    }
}
