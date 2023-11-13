package com.gordeeva.TJI_Lab2.dao.impl;

import com.gordeeva.TJI_Lab2.config.ConfigurationDBConnection;
import com.gordeeva.TJI_Lab2.dao.GuestDao;
import com.gordeeva.TJI_Lab2.model.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuestDaoImpl implements GuestDao {
    private final Connection connection = ConfigurationDBConnection.getConnection();
    private final String SQL_ADD_GUEST = "INSERT INTO guests (first_name,last_name,email,phone_number) VALUES (?,?,?,?)";
    private final String SQL_GET_GUEST_ID_LIST = "SELECT guest_id FROM guests";
    private final String SQL_GET_GUEST_BY_ID = "SELECT * FROM guests WHERE guest_id=?";
    private final String SQL_GET_ALL_GUESTS = "SELECT * FROM guests";
    private final String SQL_UPDATE_GUEST = "UPDATE guests SET first_name=?, last_name=?,email=?,phone_number=? WHERE guest_id=?";
    private final String SQL_DELETE_GUEST = "DELETE FROM guests WHERE guest_id=?";
    @Override
    public int addGuest(Guest guest) {
        int resultOfExecution = 0;
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_ADD_GUEST, Statement.RETURN_GENERATED_KEYS)){
            prepStatement.setString(1, guest.getFirstName());
            prepStatement.setString(2, guest.getLastName());
            prepStatement.setString(3, guest.getEmail());
            prepStatement.setString(4, guest.getPhoneNumber());
            resultOfExecution = prepStatement.executeUpdate();
            try (ResultSet generatedKeys = prepStatement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    guest.setId(generatedKeys.getLong(1));
                }

            }
        } catch (SQLException ex){
            ex.printStackTrace();

        }
        return resultOfExecution;
    }

    @Override
    public Optional<Guest> getGuestById(Long guestId) {
        Guest guest = new Guest();
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_GET_GUEST_BY_ID)){
            prepStatement.setLong(1, guestId);
            try (ResultSet rs = prepStatement.executeQuery()){
                while (rs.next()){
                    guest.setId(rs.getLong("guest_id"));
                    guest.setFirstName(rs.getString("first_name"));
                    guest.setLastName(rs.getString("last_name"));
                    guest.setEmail(rs.getString("email"));
                    guest.setPhoneNumber(rs.getString("phone_number"));
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return Optional.of(guest);
    }

    @Override
    public List<Guest> getAllGuests() {
        List<Guest> allGuests = new ArrayList<>();
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_GET_ALL_GUESTS)){
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                Guest guest = new Guest();
                guest.setId(rs.getLong("guest_id"));
                guest.setFirstName(rs.getString("first_name"));
                guest.setLastName(rs.getString("last_name"));
                guest.setEmail(rs.getString("email"));
                guest.setPhoneNumber(rs.getString("phone_number"));
                allGuests.add(guest);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return allGuests;
    }

    @Override
    public List<Long> getGuestsIdList() {
        List<Long> guestIdList = new ArrayList<>();
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_GET_GUEST_ID_LIST)){
            try (ResultSet rs = prepStatement.executeQuery()){
                while (rs.next()){
                    guestIdList.add(rs.getLong("guest_id"));
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return guestIdList;
    }

    @Override
    public int updateGuest(Guest guest) {
        int resultOfExecution = 0;
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_UPDATE_GUEST)){
            prepStatement.setString(1, guest.getFirstName());
            prepStatement.setString(2, guest.getLastName());
            prepStatement.setString(3, guest.getEmail());
            prepStatement.setString(4, guest.getPhoneNumber());
            prepStatement.setLong(5,guest.getId());
            resultOfExecution = prepStatement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return resultOfExecution;
    }

    @Override
    public int deleteGuest(Long guestId) {
        int resultOfExecution = 0;
        try(PreparedStatement prepStatement = connection.prepareStatement(SQL_DELETE_GUEST)){
            prepStatement.setLong(1, guestId);
            resultOfExecution = prepStatement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return resultOfExecution;
    }
}
