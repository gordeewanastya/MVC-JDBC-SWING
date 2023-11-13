package com.gordeeva.TJI_Lab2.dao;

import com.gordeeva.TJI_Lab2.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomDao {
    int addRoom(Room room);
    Optional<Room> getRoomById(Long roomId);
    List<Room> getAllRooms();
    List<Long> getRoomsIdList();
    int updateRoom(Room room);
    int deleteRoom(Long roomId);
}
