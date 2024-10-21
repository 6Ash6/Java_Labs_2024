package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import tariff.Tariff;
import tariff.CallPrice;
import tariff.Parameters;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DOMTariffParser implements TariffParser {

    private static final Logger logger = LogManager.getLogger(DOMTariffParser.class);

    @Override
    public List<Tariff> parse(String filePath) throws FileNotFoundException {
        List<Tariff> tariffs = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filePath));

        } catch (ParserConfigurationException e) {
            logger.error("Failed to create DocumentBuilder", e);
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            logger.error("File not found: {}", filePath);
            throw new FileNotFoundException(e.getMessage());
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        logger.info("Processing file: {}", filePath);
        document.getDocumentElement().normalize();

        NodeList tariffList = document.getElementsByTagName("Tariff");
        logger.debug("Found {} tariffs", tariffList.getLength());

        for (int i = 0; i < tariffList.getLength(); i++) {
            Element tariffElement = (Element) tariffList.item(i);

            String id = tariffElement.getAttribute("id");
            logger.debug("Parsing tariff: {}", id);

            String name = tariffElement.getElementsByTagName("Name").item(0).getTextContent();
            String operator = tariffElement.getElementsByTagName("OperatorName").item(0).getTextContent();
            String payroll = tariffElement.getElementsByTagName("Payroll").item(0).getTextContent();
            String smsprice = tariffElement.getElementsByTagName("SmsPrice").item(0).getTextContent();

            CallPrice callPrice = getCallPrice(tariffElement);
            Parameters parameters = getParameters(tariffElement);

            Tariff tariff = new Tariff(id, name, operator, payroll, smsprice, callPrice, parameters);
            tariffs.add(tariff);
        }

        logger.info("Successfully parsed {} tariffs", tariffs.size());

        return tariffs;
    }

    private static Parameters getParameters(Element tariffElement) {
        Parameters parameters = new Parameters();
        NodeList ParametersList = tariffElement.getElementsByTagName("Parameters");
        for (int j = 0; j < ParametersList.getLength(); j++) {
            Element paramElement = (Element) ParametersList.item(j);
            String type = paramElement.getAttribute("type");
            String value = paramElement.getTextContent();
            parameters.addParam(type, value);
        }
        return parameters;
    }

    private static CallPrice getCallPrice(Element tariffElement) {
        CallPrice callPrice = new CallPrice();
        NodeList callPriceList = tariffElement.getElementsByTagName("CallPrice");
        for (int j = 0; j < callPriceList.getLength(); j++) {
            Element paramElement = (Element) callPriceList.item(j);
            String type = paramElement.getAttribute("type");
            String value = paramElement.getTextContent();
            callPrice.addPrice(type, value);
        }
        return callPrice;
    }
}