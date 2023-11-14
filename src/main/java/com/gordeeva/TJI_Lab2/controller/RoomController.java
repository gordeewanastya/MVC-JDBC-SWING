package com.gordeeva.TJI_Lab2.controller;

import com.gordeeva.TJI_Lab2.model.Room;
import com.gordeeva.TJI_Lab2.service.RoomService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RoomController {
    private RoomService roomService;

    public int addRoom(Room room){
        return roomService.addRoom(room);
    }

    public Optional<Room> getRoomById(Long roomId){
        return roomService.getRoomById(roomId);
    }

    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    public List<Long> getRoomsIdList(){ return roomService.getRoomsIdList();}

    public int updateRoom(Room room){
        return roomService.updateRoom(room);
    }

    public int deleteRoom(Long roomId){ return roomService.deleteRoom(roomId); }
    public List<Room> getBusinessRooms() {return roomService.getBusinessRooms();}
}
