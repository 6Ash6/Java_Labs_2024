package test;

import tariff.Tariff;
import parser.SAXTariffParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class SAXTariffParserTest {

    private SAXTariffParser saxTariffParser;
    private String xmlFilePath;

    @BeforeClass
    public void setUp() {
        saxTariffParser = new SAXTariffParser();
        xmlFilePath = "src/main/java/resources/tariff.xml";
    }

    @Test
    public void testParseTariff() throws FileNotFoundException {
        List<Tariff> tariffs = saxTariffParser.parse(xmlFilePath);
        Assert.assertNotNull(tariffs, "Parsed tariffs list should not be null");
        Assert.assertFalse(tariffs.isEmpty(), "Parsed tariffs list should not be empty");
        Assert.assertEquals(tariffs.size(), 16, "Expected number of tariffs should be 16");

        Tariff firstTariff = tariffs.get(0);
        Assert.assertEquals(firstTariff.getName(), "Супер1", "First tariff name should be Супер1");
        Assert.assertEquals(firstTariff.getOperatorname(), "A1", "First tariff operator should be A1");
        Assert.assertEquals(firstTariff.getPayroll(), "15p", "15p");
        Assert.assertEquals(firstTariff.getSmsprice(), "16p", "16p");
        Assert.assertEquals(firstTariff.getCallPrice().getPrice("home"), "30 р", "30 р");
    }

    @Test
    public void testParseInvalidFile() {
        String invalidXmlFilePath = "invalid_tariffs.xml";
        Assert.assertThrows(FileNotFoundException.class, () -> saxTariffParser.parse(invalidXmlFilePath));
    }
}