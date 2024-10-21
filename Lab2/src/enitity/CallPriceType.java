package enitity;

import Exception.CustomException;

public enum CallPriceType {
    INSIDE_NETWORK("Inside network"),
    OUTSIDE_NETWORK("Outside network"),
    LANDLINE("Landline");

    private String value;

    CallPriceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CallPriceType getCallPriceTypeByValue(String value) throws CustomException {
        for (CallPriceType type : CallPriceType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new CustomException("Unknown call price type: " + value);
    }
}

