package sabatinoprovenza.BE_S7_L5.payloads;

import java.time.LocalDate;

public record EventUpdateDTO(String title,
                             String description,
                             LocalDate date,
                             String location,
                             Integer maxSeats) {
}
