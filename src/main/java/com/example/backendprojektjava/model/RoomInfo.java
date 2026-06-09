package com.example.backendprojektjava.model;

public class RoomInfo {

    private String roomType;
    private int availableRooms;
    private int maxGuests;
    private int pricePerNight;

    public RoomInfo(String roomType, int availableRooms, int maxGuests, int pricePerNight) {
        this.roomType = roomType;
        this.availableRooms = availableRooms;
        this.maxGuests = maxGuests;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}