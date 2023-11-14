package com.gordeeva.TJI_Lab2.dao.impl;

import com.gordeeva.TJI_Lab2.config.ConfigurationDBConnection;
import com.gordeeva.TJI_Lab2.dao.RoomDao;
import com.gordeeva.TJI_Lab2.model.Guest;
import com.gordeeva.TJI_Lab2.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDaoImpl implements RoomDao {
    private final Connection connection = ConfigurationDBConnection.getConnection();
    private final String SQL_ADD_ROOM = "INSERT INTO rooms (hotel_id,room_number,room_type,rate) VALUES (?,?,?,?)";
    private final String SQL_GET_ROOMS_ID_LIST = "SELECT room_id FROM rooms";
    private final String SQL_GET_ROOM_BY_ID = "SELECT * FROM rooms WHERE room_id=?";
    private final String SQL_GET_ALL_ROOMS = "SELECT * FROM rooms";
    private final String SQL_UPDATE_ROOM = "UPDATE rooms SET hotel_id=?,room_number=?,room_type=?,rate=? WHERE room_id=?";
    private final String SQL_DELETE_ROOM = "DELETE FROM rooms WHERE room_id=?";
    private final String SQL_GET_BUSINESS_ROOMS = "SELECT * FROM rooms WHERE room_type='Business'";

    @Override
    public int addRoom(Room room) {
        int resultOfExecution = 0;
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_ADD_ROOM, Statement.RETURN_GENERATED_KEYS)){
            prepStatement.setLong(1, room.getHotelId());
            prepStatement.setString(2, room.getRoomNumber());
            prepStatement.setString(3, room.getRoomType());
            prepStatement.setFloat(4, room.getRate());
            resultOfExecution = prepStatement.executeUpdate();
            try (ResultSet generatedKeys = prepStatement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    room.setId(generatedKeys.getLong(1));
                }

            }
        } catch (SQLException ex){
            ex.printStackTrace();

        }
        return resultOfExecution;
    }

    @Override
    public Optional<Room> getRoomById(Long roomId) {
        Room room = new Room();
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_GET_ROOM_BY_ID)){
            prepStatement.setLong(1, roomId);
            try (ResultSet rs = prepStatement.executeQuery()){
                while (rs.next()){
                    room.setId(rs.getLong("room_id"));
                    room.setHotelId(rs.getLong("hotel_id"));
                    room.setRoomNumber(rs.getString("room_number"));
                    room.setRoomType(rs.getString("room_type"));
                    room.setRate(rs.getFloat("rate"));
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return Optional.of(room);
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> allRooms = new ArrayList<>();
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_GET_ALL_ROOMS)){
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getLong("room_id"));
                room.setHotelId(rs.getLong("hotel_id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setRoomType(rs.getString("room_type"));
                room.setRate(rs.getFloat("rate"));
                allRooms.add(room);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return allRooms;
    }

    @Override
    public List<Long> getRoomsIdList() {
        List<Long> roomIdList = new ArrayList<>();
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_GET_ROOMS_ID_LIST)){
            try (ResultSet rs = prepStatement.executeQuery()){
                while (rs.next()){
                    roomIdList.add(rs.getLong("room_id"));
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return roomIdList;
    }

    @Override
    public int updateRoom(Room room) {
        int resultOfExecution = 0;
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_UPDATE_ROOM)){
            prepStatement.setLong(1, room.getHotelId());
            prepStatement.setString(2, room.getRoomNumber());
            prepStatement.setString(3, room.getRoomType());
            prepStatement.setFloat(4, room.getRate());
            prepStatement.setLong(5,room.getId());
            resultOfExecution = prepStatement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return resultOfExecution;
    }

    @Override
    public int deleteRoom(Long roomId) {
        int resultOfExecution = 0;
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_DELETE_ROOM)){
            prepStatement.setLong(1, roomId);
            resultOfExecution = prepStatement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return resultOfExecution;
    }

    @Override
    public List<Room> getBusinessRooms() {
        List<Room> allBusinessRooms = new ArrayList<>();
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_GET_BUSINESS_ROOMS)){
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getLong("room_id"));
                room.setHotelId(rs.getLong("hotel_id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setRoomType(rs.getString("room_type"));
                room.setRate(rs.getFloat("rate"));
                allBusinessRooms.add(room);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return allBusinessRooms;
    }
}
