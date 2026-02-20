package sabatinoprovenza.BE_S7_L5.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationExceptions extends RuntimeException {
    private final List<String> errorsList;

    public ValidationExceptions(List<String> errorsList) {
        this.errorsList = errorsList;
    }
}
