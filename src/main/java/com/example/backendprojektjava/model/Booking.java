package com.example.backendprojektjava.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Booking {

    private Long id;

    @NotBlank(message = "Namn får inte vara tomt.")
    private String guestName;

    @NotBlank(message = "Rumstyp får inte vara tom.")
    @Pattern(
            regexp = "Enkelrum|Dubbelrum|Svit",
            message = "Rumstyp måste vara Enkelrum, Dubbelrum eller Svit."
    )
    private String roomType;

    @Min(value = 1, message = "Antal gäster måste vara minst 1.")
    @Max(value = 3, message = "Antal gäster får vara max 3.")
    private int numberOfGuests;

    @Min(value = 1, message = "Antal nätter måste vara minst 1.")
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
