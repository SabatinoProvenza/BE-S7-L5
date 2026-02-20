package sabatinoprovenza.BE_S7_L5.payloads;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookingResponseDTO(UUID id, UUID eventId, UUID userId, LocalDateTime createdAt) {
}
