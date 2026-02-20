package sabatinoprovenza.BE_S7_L5.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sabatinoprovenza.BE_S7_L5.entities.Event;
import sabatinoprovenza.BE_S7_L5.entities.User;
import sabatinoprovenza.BE_S7_L5.exceptions.NotFoundException;
import sabatinoprovenza.BE_S7_L5.exceptions.UnauthorizedException;
import sabatinoprovenza.BE_S7_L5.payloads.EventDTO;
import sabatinoprovenza.BE_S7_L5.payloads.EventResponseDTO;
import sabatinoprovenza.BE_S7_L5.payloads.EventUpdateDTO;
import sabatinoprovenza.BE_S7_L5.repositories.BookingRepository;
import sabatinoprovenza.BE_S7_L5.repositories.EventRepository;

import java.util.List;
import java.util.UUID;


@Service
public class EventService {
    private final UserService userService;
    private final EventRepository eventRepository;
    private final BookingRepository bookingRepository;

    public EventService(UserService userService, EventRepository eventRepository, BookingRepository bookingRepository) {
        this.userService = userService;
        this.eventRepository = eventRepository;
        this.bookingRepository = bookingRepository;
    }

    public EventResponseDTO createEvent(EventDTO request, String email) {
        User organizer = userService.findByEmail(email);

        Event event = new Event(request.title(), request.description(), request.date(), request.location(), request.maxSeats(), organizer);
        Event saved = eventRepository.save(event);

        return new EventResponseDTO(saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getDate(),
                saved.getLocation(),
                saved.getMaxSeats(),
                organizer.getUsername());
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Event updateEvent(UUID id, EventUpdateDTO req, User user) {
        Event e = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));

        if (!e.getOrganizer().getId().equals(user.getId())) {
            throw new UnauthorizedException("Non puoi modificare eventi di altri");
        }

        if (req.title() != null) e.setTitle(req.title());
        if (req.description() != null) e.setDescription(req.description());
        if (req.date() != null) e.setDate(req.date());
        if (req.location() != null) e.setLocation(req.location());
        if (req.maxSeats() != null) e.setMaxSeats(req.maxSeats());

        return eventRepository.save(e);
    }

    @Transactional
    public void deleteEvent(UUID id, User user) {
        Event e = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));

        boolean isOwner = e.getOrganizer().getId().equals(user.getId());
        if (!isOwner) throw new UnauthorizedException("Non puoi eliminare questo evento");

        bookingRepository.deleteByEventId(id);
        eventRepository.delete(e);
    }
}
