package sabatinoprovenza.BE_S7_L5.services;

import org.springframework.stereotype.Service;
import sabatinoprovenza.BE_S7_L5.entities.User;
import sabatinoprovenza.BE_S7_L5.exceptions.NotFoundException;
import sabatinoprovenza.BE_S7_L5.repositories.UserRepository;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("L'utente con email " + email + " non è stato trovato!"));
    }

    public User findById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("L'utente con id: " + id + " non è stato trovato!"));
    }
}
