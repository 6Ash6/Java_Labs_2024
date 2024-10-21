package main;

import parser.AbstractTariffBuilder;
import parser.TariffBuilderFactory;

public class Main {
    public static void main(String[] args) {
        String type = "stax"; // можно использовать "dom", "sax", "stax"
        AbstractTariffBuilder builder = TariffBuilderFactory.createTariffBuilder(type);
        builder.buildSetTariffs("tariffs.xml");
        System.out.println(builder.getTariffs());
    }
}
