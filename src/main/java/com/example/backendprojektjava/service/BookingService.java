package com.example.backendprojektjava.service;

import com.example.backendprojektjava.exception.BookingNotFoundException;
import com.example.backendprojektjava.exception.GuestCapacityException;
import com.example.backendprojektjava.exception.RoomFullyBookedException;
import com.example.backendprojektjava.exception.RoomNotFoundException;
import com.example.backendprojektjava.model.Booking;
import com.example.backendprojektjava.model.RoomInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();
    private long nextId = 1;

    private final RoomService roomService;

    public BookingService(RoomService roomService) {
        this.roomService = roomService;
    }

    public Booking createBooking(Booking booking) {
        RoomInfo room = roomService.findRoomByType(booking.getRoomType());

        if (room == null) {
            throw new RoomNotFoundException("Rumstypen finns inte.");
        }

        if (booking.getNumberOfGuests() > room.getMaxGuests()) {
            throw new GuestCapacityException(
                    room.getRoomType() + " tillåter max " + room.getMaxGuests() + " gäster."
            );
        }

        if (room.getAvailableRooms() <= 0) {
            throw new RoomFullyBookedException(room.getRoomType() + " är fullbokat.");
        }

        booking.setId(nextId++);
        booking.setTotalPrice(room.getPricePerNight() * booking.getNights());

        room.setAvailableRooms(room.getAvailableRooms() - 1);

        bookings.add(booking);

        return booking;
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public void deleteBooking(Long id) {
        Booking bookingToRemove = bookings.stream()
                .filter(booking -> booking.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookingNotFoundException("Bokningen hittades inte."));

        RoomInfo room = roomService.findRoomByType(bookingToRemove.getRoomType());

        if (room != null) {
            room.setAvailableRooms(room.getAvailableRooms() + 1);
        }

        bookings.remove(bookingToRemove);
    }
}