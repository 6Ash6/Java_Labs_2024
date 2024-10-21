package parser;

import tariff.CallPrice;
import tariff.Parameters;
import tariff.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SAXTariffParser implements TariffParser {

    private static final Logger logger = LogManager.getLogger(SAXTariffParser.class);

    @Override
    public List<Tariff> parse(String filePath) throws RuntimeException, FileNotFoundException {
        List<Tariff> tariffs = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + filePath);
            }

            logger.info("Processing file: {}", filePath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(file, new TariffHandler(tariffs));
            logger.info("Successfully parsed {} tariffs", tariffs.size());
        } catch (FileNotFoundException e) {
            logger.error("File not found: {}", filePath);
            throw new FileNotFoundException(e.getMessage());
        } catch (Exception e) {
            logger.error("Error while parsing {}: {}", filePath, e.getMessage());
            throw new RuntimeException(e);
        }
        return tariffs;
    }

    private static class TariffHandler extends DefaultHandler {
        private static final String TARIFF_TAG = "Tariff";
        private static final String NAME_TAG = "Name";
        private static final String OPERATOR_TAG = "OperatorName";
        private static final String PAY_ROLL_TAG = "Payroll";
        private static final String SMS_PRICE_TAG = "SmsPrice";
        private static final String PARAMETERS_TAG = "Parameters";
        private static final String CALL_PRICE_TAG = "CallPrice";

        private List<Tariff> tariffs;
        private Tariff currentTariff;
        private StringBuilder currentValue;
        private String currentParameterType;
        private String currentPriceType;

        public TariffHandler(List<Tariff> tariffs) {
            this.tariffs = tariffs;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (TARIFF_TAG.equals(qName)) {
                String id = attributes.getValue("id");
                currentTariff = new Tariff(id, "", "", "","", new CallPrice(), new Parameters());
                logger.debug("Parsing tariff: {}", id);
            } else if (PARAMETERS_TAG.equals(qName)) {
                currentParameterType = attributes.getValue("type");
            } else if (CALL_PRICE_TAG.equals(qName)) {
                currentPriceType = attributes.getValue("type");
            }
            currentValue = new StringBuilder();
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            currentValue.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (TARIFF_TAG.equals(qName)) {
                tariffs.add(currentTariff);
            } else if (NAME_TAG.equals(qName)) {
                currentTariff.setName(currentValue.toString().trim());
            } else if (OPERATOR_TAG.equals(qName)) {
                currentTariff.setOperatorname(currentValue.toString().trim());
            } else if (PAY_ROLL_TAG.equals(qName)) {
                currentTariff.setPayroll(currentValue.toString().trim());
            } else if (SMS_PRICE_TAG.equals(qName) && currentTariff != null) {
                String value = currentValue.toString().trim();
                currentTariff.setSmsPrice(currentValue.toString().trim());
            } else if (PARAMETERS_TAG.equals(qName) && currentTariff != null) {
                String value = currentValue.toString().trim();
                currentTariff.getParameters().addParam(currentParameterType, value);
            } else if (CALL_PRICE_TAG.equals(qName)) {
                String value = currentValue.toString().trim();
            }

            if (PARAMETERS_TAG.equals(qName)) {
                currentParameterType = null;
            } else if (CALL_PRICE_TAG.equals(qName)) {
                currentPriceType = null;
            }
        }
    }
}