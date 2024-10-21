package parser;

public class TariffBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    private TariffBuilderFactory() {
    }

    public static TariffsSaxBuilder createTariffBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM: {
                return new TariffsDomBuilder();
            }
            case STAX: {
                return new TariffsStaxBuilder();
            }
            case SAX: {
                return new TariffsSaxBuilder();
            }
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
