package com.gordeeva.TJI_Lab2.service;

import com.gordeeva.TJI_Lab2.dao.impl.RoomDaoImpl;
import com.gordeeva.TJI_Lab2.model.Room;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RoomService {
    private final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();

    public int addRoom(Room room){
        return roomDaoImpl.addRoom(room);
    }

    public Optional<Room> getRoomById(Long roomId){
        return roomDaoImpl.getRoomById(roomId);
    }

    public List<Room> getAllRooms(){
        return roomDaoImpl.getAllRooms();
    }

    public List<Long> getRoomsIdList(){ return roomDaoImpl.getRoomsIdList();}

    public int updateRoom(Room room){
        return roomDaoImpl.updateRoom(room);
    }

    public int deleteRoom(Long roomId){
        return roomDaoImpl.deleteRoom(roomId);
    }
}
