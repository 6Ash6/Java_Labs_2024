package parser;

public enum TariffXmlTag {
    TARIFFS("tariffs"),

    TARIFF("tariff"),

    ID("id"),

    NAME("name"),

    OPERATOR_NAME("operator_name"),

    PAYROLL("payroll"),

    CALL_PRICES("call_prices"),

    INSIDE_NETWORK("inside_network"),

    OUTSIDE_NETWORK("outside_network"),

    STATIONARY_PHONE("stationary_phone"),

    SMS_PRICE("sms_price"),

    PARAMETERS("parameters"),

    FAVORITE_NUMBER("favorite_number"),

    TARIFFICATION("tariffication"),

    CONNECTION_FEE("connection_fee");

    private String value;

    TariffXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
