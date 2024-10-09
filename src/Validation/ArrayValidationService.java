package Validation;

public class ArrayValidationService {

    // Проверка строки на валидность (разделитель может быть запятой или дефисом)
    public boolean isValidArrayString(String line) {
        return line.matches("^\\d+(,\\d+)*$") || line.matches("^\\d+(-\\d+)*$");
    }

    // Преобразование строки в массив
    public int[] parseStringToArray(String line) {
        if (line.contains(",")) {
            String[] parts = line.split(",");
            return convertStringArrayToIntArray(parts);
        } else if (line.contains("-")) {
            String[] parts = line.split("-");
            return convertStringArrayToIntArray(parts);
        }
        return new int[0];
    }

    private int[] convertStringArrayToIntArray(String[] parts) {
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i].trim());
        }
        return result;
    }
}
