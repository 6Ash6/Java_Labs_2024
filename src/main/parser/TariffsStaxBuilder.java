package parser;

import entity.*;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TariffsStaxBuilder extends AbstractTariffsBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    private XMLInputFactory inputFactory;

    public TariffsStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        tariffs = new HashSet<>();
    }

    public TariffsStaxBuilder(Set<Tariff> tariffs) {
        super(tariffs);
    }

    @Override
    public void buildSetTariffs(String fileName) {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(TariffXmlTag.TARIFF.getValue())) {
                        Tariff tariff = new Tariff();
                        buildTariff(reader, tariff);
                        tariffs.add(tariff);
                    } else if (name.equals(TariffXmlTag.PREMIUM_TARIFF.getValue())) {
                        PremiumTariff tariff = new PremiumTariff();
                        buildPremiumTariff(reader, tariff);
                        tariffs.add(tariff);
                    }
                }
            }
        } catch (XMLStreamException | CustomException | IOException e) {
            e.printStackTrace();
            LOGGER.error("Ex: ", e);
        }
    }

    private Tariff buildTariff(XMLStreamReader reader, Tariff tariff)
            throws XMLStreamException, CustomException {
        tariff.setId(reader.getAttributeValue(null, TariffXmlTag.ID.getValue()));
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    String name = reader.getLocalName();
                    TariffXmlTag tag = TariffXmlTag.valueOf(name.toUpperCase());
                    switch (tag) {
                        case NAME:
                            tariff.setName(getXMLText(reader));
                            break;
                        case OPERATOR:
                            tariff.setOperator(getXMLText(reader));
                            break;
                        case SUBSCRIPTION_FEE:
                            tariff.setSubscriptionFee(Double.parseDouble(getXMLText(reader)));
                            break;
                        case CALL_PRICE:
                            tariff.setCallPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                        case SMS_PRICE:
                            tariff.setSmsPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                        case PARAMETERS:
                            tariff.setParameters(getXMLText(reader)); // Пример использования текстовых параметров
                            break;
                        default:
                            throw new CustomException("Unknown tag: " + name);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (TariffXmlTag.valueOf(name.toUpperCase()) == TariffXmlTag.TARIFF ||
                        TariffXmlTag.valueOf(name.toUpperCase()) == TariffXmlTag.PREMIUM_TARIFF) {
                        return tariff;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <tariff>");
    }

    private PremiumTariff buildPremiumTariff(XMLStreamReader reader, PremiumTariff tariff) throws XMLStreamException, CustomException {
        tariff.setExtraBenefits(getXMLText(reader)); // Пример дополнительных параметров для премиум-тарифов
        return (PremiumTariff) buildTariff(reader, tariff);
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
