package sabatinoprovenza.BE_S7_L5.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sabatinoprovenza.BE_S7_L5.exceptions.ValidationExceptions;
import sabatinoprovenza.BE_S7_L5.payloads.LoginRequest;
import sabatinoprovenza.BE_S7_L5.payloads.LoginResponseDTO;
import sabatinoprovenza.BE_S7_L5.payloads.RegisterRequest;
import sabatinoprovenza.BE_S7_L5.payloads.RegisterResponseDTO;
import sabatinoprovenza.BE_S7_L5.services.AuthService;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponseDTO register(@RequestBody @Validated RegisterRequest body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {

            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationExceptions(errorsList);
        }
        return authService.register(body);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Validated LoginRequest body) {

        return new LoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }
}
