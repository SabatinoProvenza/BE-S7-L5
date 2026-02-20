package sabatinoprovenza.BE_S7_L5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sabatinoprovenza.BE_S7_L5.entities.Role;

public record RegisterRequest(@NotBlank(message = "username obbligatorio")
                              String username,

                              @NotBlank(message = "email obbligatoria")
                              @Email
                              String email,

                              @NotBlank(message = "password obbligatoria")
                              String password,

                              @NotNull
                              Role role
) {
}
