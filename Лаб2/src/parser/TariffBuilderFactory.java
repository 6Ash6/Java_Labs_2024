package parser;

public class TariffBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    private TariffBuilderFactory() {
    }

    public static AbstractTariffBuilder createTariffBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM: {
                return new TariffDomBuilder();
            }
            case STAX: {
                return new TariffStaxBuilder();
            }
            case SAX: {
                return new TariffSaxBuilder();
            }
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
