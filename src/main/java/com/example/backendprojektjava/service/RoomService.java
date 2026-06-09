package com.example.backendprojektjava.service;

import com.example.backendprojektjava.model.RoomInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final List<RoomInfo> rooms = new ArrayList<>();

    public RoomService() {
        rooms.add(new RoomInfo("Enkelrum", 10, 1, 500));
        rooms.add(new RoomInfo("Dubbelrum", 7, 2, 1000));
        rooms.add(new RoomInfo("Svit", 3, 3, 2000));
    }

    public List<RoomInfo> getAllRooms() {
        return rooms;
    }

    public RoomInfo findRoomByType(String roomType) {
        return rooms.stream()
                .filter(room -> room.getRoomType().equalsIgnoreCase(roomType))
                .findFirst()
                .orElse(null);
    }
}