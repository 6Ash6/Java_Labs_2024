package parser;

import entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class TariffsSaxBuilder extends AbstractTariffsBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    private TariffsHandler handler = new TariffsHandler();
    private XMLReader reader;

    public TariffsSaxBuilder() {
        // Настройка XMLReader
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
            reader.setErrorHandler(new TariffErrorHandler());
            reader.setContentHandler(handler);
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.error("ParserConfigurationException or SAXException", e);
            e.printStackTrace(); // Логирование ошибки
        }
    }

    public TariffsSaxBuilder(Set<Tariff> tariffs) {
        super(tariffs);
        // Дополнительная настройка, если необходимо
    }

    @Override
    public void buildSetTariffs(String fileName) {
        try {
            reader.parse(fileName);
        } catch (IOException | SAXException e) {
            LOGGER.error("IOException or SAXException while parsing the file", e);
            e.printStackTrace(); // Логирование ошибки
        }
        tariffs = handler.getTariffs(); // Получение списка тарифов
    }
}
