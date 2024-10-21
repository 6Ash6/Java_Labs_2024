package parser;

import builder.TariffBuilder;
import enitity.Tariff;
import Exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TariffsDomBuilder extends AbstractTariffsBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public TariffsDomBuilder() {
        tariffs = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("ParserConfigurationException or SAXException", e);
            e.printStackTrace();
        }
    }

    public TariffsDomBuilder(Set<Tariff> tariffs) {
        super(tariffs);
    }

    @Override
    public void buildSetTariffs(String fileName) {
        Document doc;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList tariffsList = root.getElementsByTagName("tariff");
            for (int i = 0; i < tariffsList.getLength(); i++) {
                Element tariffElement = (Element) tariffsList.item(i);
                Tariff tariff = buildTariff(tariffElement);
                tariffs.add(tariff);
            }
        } catch (IOException | SAXException | CustomException e) {
            LOGGER.error("ParserConfigurationException or SAXException", e);
            e.printStackTrace();
        }
    }

    private Tariff buildTariff(Element tariffElement) throws CustomException {
        return new TariffBuilder()
                .id(tariffElement.getAttribute("id"))
                .name(getElementTextContent(tariffElement, "name"))
                .operatorName(getElementTextContent(tariffElement, "operator_name"))
                .payroll(Double.parseDouble(getElementTextContent(tariffElement, "payroll")))
                .callPrices(buildCallPrices(tariffElement))
                .smsPrice(Double.parseDouble(getElementTextContent(tariffElement, "sms_price")))
                .parameters(buildParameters(tariffElement))
                .build();
    }

    private Tariff.CallPrices buildCallPrices(Element tariffElement) {
        Tariff.CallPrices callPrices = new Tariff.CallPrices();
        Element callPricesElement = (Element) tariffElement.getElementsByTagName("call_prices").item(0);
        callPrices.setInsideNetwork(Double.parseDouble(getElementTextContent(callPricesElement, "inside_network")));
        callPrices.setOutsideNetwork(Double.parseDouble(getElementTextContent(callPricesElement, "outside_network")));
        callPrices.setStationaryPhone(Double.parseDouble(getElementTextContent(callPricesElement, "stationary_phone")));
        return callPrices;
    }

    private Tariff.Parameters buildParameters(Element tariffElement) {
        Tariff.Parameters parameters = new Tariff.Parameters();
        Element parametersElement = (Element) tariffElement.getElementsByTagName("parameters").item(0);
        parameters.setFavoriteNumber(Integer.parseInt(getElementTextContent(parametersElement, "favorite_number")));
        parameters.setTariffication(getElementTextContent(parametersElement, "tariffication"));
        parameters.setConnectionFee(Double.parseDouble(getElementTextContent(parametersElement, "connection_fee")));
        return parameters;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
