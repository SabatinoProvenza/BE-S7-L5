package sabatinoprovenza.BE_S7_L5.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sabatinoprovenza.BE_S7_L5.entities.Event;
import sabatinoprovenza.BE_S7_L5.entities.User;
import sabatinoprovenza.BE_S7_L5.exceptions.ValidationExceptions;
import sabatinoprovenza.BE_S7_L5.payloads.EventDTO;
import sabatinoprovenza.BE_S7_L5.payloads.EventResponseDTO;
import sabatinoprovenza.BE_S7_L5.services.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public EventResponseDTO create(@RequestBody @Validated EventDTO body, @AuthenticationPrincipal User currentUser, BindingResult validationResult) {

        if (validationResult.hasErrors()) {

            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationExceptions(errorsList);
        }

        return eventService.createEvent(body, currentUser.getEmail());
    }

    @GetMapping
    public List<Event> findAllEvents() {
        return eventService.findAllEvents();
    }
}
