package sabatinoprovenza.BE_S7_L5.payloads;

import sabatinoprovenza.BE_S7_L5.entities.Role;

import java.util.UUID;

public record RegisterResponseDTO(UUID id,
                                  String username,
                                  String email,
                                  Role role) {
}
