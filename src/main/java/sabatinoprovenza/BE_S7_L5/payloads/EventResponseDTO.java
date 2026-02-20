package sabatinoprovenza.BE_S7_L5.payloads;

import java.time.LocalDate;
import java.util.UUID;

public record EventResponseDTO(UUID id,
                               String title,
                               String description,
                               LocalDate date,
                               String location,
                               int maxSeats,
                               String organizerUsername) {
}
