package Validation;

import Exception.InvalidArrayFormatException;

public class ArrayValidator {
    // Регулярное выражение для проверки строки на допустимые символы
    private static final String VALID_PATTERN = "^[0-9,\\s-]+$";

    public boolean isValid(String line) throws InvalidArrayFormatException {
        if (!line.matches(VALID_PATTERN)) {
            throw new InvalidArrayFormatException("Строка содержит недопустимые символы: " + line);
        }
        return true;
    }
}
