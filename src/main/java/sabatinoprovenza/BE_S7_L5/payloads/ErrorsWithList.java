package sabatinoprovenza.BE_S7_L5.payloads;

import java.util.List;

public record ErrorsWithList(String message, List<String> errorsList) {
}
