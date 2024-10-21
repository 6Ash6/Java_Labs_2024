package enitity;

import Exception.CustomException;

public enum Parameter {
    FAVORITE_NUMBER("Favorite number"),
    TARIFFICATION("Tariffication"),
    CONNECTION_FEE("Connection fee");

    private String value;

    Parameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Parameter getParameterByValue(String value) throws CustomException {
        for (Parameter parameter : Parameter.values()) {
            if (parameter.value.equalsIgnoreCase(value)) {
                return parameter;
            }
        }
        throw new CustomException("Unknown parameter: " + value);
    }
}
