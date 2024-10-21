package parser;

import tariff.Tariff;
import tariff.CallPrice;
import tariff.Parameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.EndElement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class StAXTariffParser implements TariffParser {

    private static final Logger logger = LogManager.getLogger(StAXTariffParser.class);

    @Override
    public List<Tariff> parse(String filePath) throws FileNotFoundException {
        List<Tariff> flowers = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader;
        try {
            eventReader = factory.createXMLEventReader(new FileReader(filePath));
        } catch (FileNotFoundException e)  {
            logger.error("File not found: {}", filePath);
            throw new FileNotFoundException(e.getMessage());
        }catch (XMLStreamException e) {
            logger.error("Error while parsing {}: {}", filePath, e.getMessage());
            throw new RuntimeException(e);
        }
        logger.info("Processing file: {}", filePath);

        Tariff currentTariff = null;
        StringBuilder currentValue = new StringBuilder();
        String currentType = null;

        while (eventReader.hasNext()) {
            XMLEvent event;
            try {
                event = eventReader.nextEvent();
            } catch (XMLStreamException e) {
                logger.error("Error while parsing {}: {}", filePath, e.getMessage());
                throw new RuntimeException(e);
            }

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String qName = startElement.getName().getLocalPart();

                if ("Tariff".equals(qName)) {
                    String id = startElement.getAttributeByName(new javax.xml.namespace.QName("id")).getValue();
                    currentTariff = new Tariff(id, "", "", "", "", new CallPrice(), new Parameters());
                    logger.debug("Parsing tariff: {}", currentTariff.getId());
                } else if ("CallPrice".equals(qName) || "Parameters".equals(qName)) {
                    currentType = startElement.getAttributeByName(new javax.xml.namespace.QName("type")).getValue();
                }
            } else if (event.isCharacters()) {
                currentValue.append(event.asCharacters().getData());
            } else if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String qName = endElement.getName().getLocalPart();

                if ("Tariff".equals(qName)) {
                    if (currentTariff != null) {
                        flowers.add(currentTariff);
                    }
                } else if ("Name".equals(qName)) {
                    if (currentTariff != null) {
                        currentTariff.setName(currentValue.toString().trim());
                    }
                } else if ("OperatorName".equals(qName)) {
                    if (currentTariff != null) {
                        currentTariff.setOperatorname(currentValue.toString().trim());
                    }
                } else if ("Payroll".equals(qName)) {
                    if (currentTariff != null) {
                        currentTariff.setPayroll(currentValue.toString().trim());
                    }
                } else if ("SmsPrice".equals(qName)) {
                    if (currentTariff != null) {
                        currentTariff.setSmsPrice(currentValue.toString().trim());
                    }
                } else if ("CallPrice".equals(qName) && currentTariff != null) {
                    String value = currentValue.toString().trim();
                    currentTariff.getCallPrice().addPrice(currentType, value);
                } else if ("Parameters".equals(qName) && currentTariff != null) {
                    String value = currentValue.toString().trim();
                    currentTariff.getParameters().addParam(currentType, value);
                }

                currentValue.setLength(0);
            }
        }

        logger.info("Successfully parsed {} flowers", flowers.size());

        return flowers;
    }
}
