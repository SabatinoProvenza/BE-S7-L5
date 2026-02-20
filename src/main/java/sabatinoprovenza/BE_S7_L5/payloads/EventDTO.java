package sabatinoprovenza.BE_S7_L5.payloads;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventDTO(@NotBlank(message = "Titolo obbligatorio")
                       String title,

                       @NotBlank(message = "Descrizione obbligatoria")
                       String description,

                       @NotNull(message = "Data obbligatoria")
                       LocalDate date,

                       @NotBlank(message = "Location obbligatoria")
                       String location,

                       @NotNull(message = "Inserire i posti massimi")
                       @Min(1)
                       @Max(100000)
                       Integer maxSeats) {
}
