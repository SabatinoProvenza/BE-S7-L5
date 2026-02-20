package sabatinoprovenza.BE_S7_L5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank
                           @Email(message = "email obbligatoria") String email,

                           @NotBlank(message = "password obbligatoria") String password) {
}
