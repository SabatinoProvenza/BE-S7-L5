package sabatinoprovenza.BE_S7_L5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sabatinoprovenza.BE_S7_L5.entities.Booking;
import sabatinoprovenza.BE_S7_L5.entities.User;
import sabatinoprovenza.BE_S7_L5.payloads.BookingResponseDTO;
import sabatinoprovenza.BE_S7_L5.services.BookingService;

import java.util.UUID;

@RestController
@RequestMapping("/events")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/{eventId}/book")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponseDTO book(@PathVariable UUID eventId,
                                   @AuthenticationPrincipal User user) {

        Booking b = bookingService.book(eventId, user);

        return new BookingResponseDTO(
                b.getId(),
                b.getEvent().getId(),
                b.getUser().getId(),
                b.getCreatedAt()
        );
    }
}
