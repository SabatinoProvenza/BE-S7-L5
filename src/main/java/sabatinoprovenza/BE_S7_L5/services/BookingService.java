package sabatinoprovenza.BE_S7_L5.services;

import org.springframework.stereotype.Service;
import sabatinoprovenza.BE_S7_L5.entities.Booking;
import sabatinoprovenza.BE_S7_L5.entities.Event;
import sabatinoprovenza.BE_S7_L5.entities.User;
import sabatinoprovenza.BE_S7_L5.exceptions.BadRequestException;
import sabatinoprovenza.BE_S7_L5.exceptions.NotFoundException;
import sabatinoprovenza.BE_S7_L5.repositories.BookingRepository;
import sabatinoprovenza.BE_S7_L5.repositories.EventRepository;

import java.util.UUID;

@Service
public class BookingService {
    private final EventRepository eventRepository;
    private final BookingRepository bookingRepository;

    public BookingService(EventRepository eventRepository, BookingRepository bookingRepository) {
        this.eventRepository = eventRepository;
        this.bookingRepository = bookingRepository;
    }

    public Booking book(UUID eventId, User user) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));

        if (bookingRepository.existsByEventIdAndUserId(eventId, user.getId())) {
            throw new BadRequestException("Sei già prenotato per questo evento");
        }

        long booked = bookingRepository.countByEventId(eventId);
        if (booked >= event.getMaxSeats()) {
            throw new BadRequestException("Evento sold out");
        }

        return bookingRepository.save(new Booking(user, event));
    }
}
