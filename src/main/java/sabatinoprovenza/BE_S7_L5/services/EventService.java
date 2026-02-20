package sabatinoprovenza.BE_S7_L5.services;

import org.springframework.stereotype.Service;
import sabatinoprovenza.BE_S7_L5.entities.Event;
import sabatinoprovenza.BE_S7_L5.entities.User;
import sabatinoprovenza.BE_S7_L5.payloads.EventDTO;
import sabatinoprovenza.BE_S7_L5.payloads.EventResponseDTO;
import sabatinoprovenza.BE_S7_L5.repositories.EventRepository;

import java.util.List;


@Service
public class EventService {
    private final UserService userService;
    private final EventRepository eventRepository;

    public EventService(UserService userService, EventRepository eventRepository) {
        this.userService = userService;
        this.eventRepository = eventRepository;
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
}
