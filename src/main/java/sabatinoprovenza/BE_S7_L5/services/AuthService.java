package sabatinoprovenza.BE_S7_L5.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sabatinoprovenza.BE_S7_L5.entities.User;
import sabatinoprovenza.BE_S7_L5.exceptions.BadRequestException;
import sabatinoprovenza.BE_S7_L5.exceptions.UnauthorizedException;
import sabatinoprovenza.BE_S7_L5.payloads.LoginRequest;
import sabatinoprovenza.BE_S7_L5.payloads.RegisterRequest;
import sabatinoprovenza.BE_S7_L5.payloads.RegisterResponseDTO;
import sabatinoprovenza.BE_S7_L5.repositories.UserRepository;
import sabatinoprovenza.BE_S7_L5.security.JWTTools;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTTools jwtTools;

    public AuthService(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder, JWTTools jwtTools) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTools = jwtTools;
    }

    public RegisterResponseDTO register(RegisterRequest request) {
        if (userService.existsByUsername(request.username())) {
            throw new BadRequestException("Username già in uso");
        }
        if (userService.existsByEmail(request.email())) {
            throw new BadRequestException("Email già in uso");
        }

        User user = new User(request.username(), request.email(), passwordEncoder.encode(request.password()), request.role());
        User saved = userRepository.save(user);
        return new RegisterResponseDTO(saved.getId(), saved.getUsername(), saved.getEmail(), saved.getRole());
    }

    public String checkCredentialsAndGenerateToken(LoginRequest body) {

        User user = this.userService.findByEmail(body.email());

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            return jwtTools.generateToken(user);

        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }


    }
}
