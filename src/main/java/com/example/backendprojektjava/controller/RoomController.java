package com.example.backendprojektjava.controller;

import com.example.backendprojektjava.model.RoomInfo;
import com.example.backendprojektjava.service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomInfo> getAllRooms() {
        return roomService.getAllRooms();
    }
}