package com.example.backendprojektjava.model;

public class Booking {

    private Long id;
    private String guestName;
    private String roomType;
    private int numberOfGuests;
    private int nights;
    private int totalPrice;

    public Booking() {
    }

    public Booking(Long id, String guestName, String roomType, int numberOfGuests, int nights, int totalPrice) {
        this.id = id;
        this.guestName = guestName;
        this.roomType = roomType;
        this.numberOfGuests = numberOfGuests;
        this.nights = nights;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public int getNights() {
        return nights;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}