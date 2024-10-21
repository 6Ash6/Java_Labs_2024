package test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import parser.StAXTariffParser;
import tariff.Tariff;

import java.io.FileNotFoundException;
import java.util.List;

public class StAXTariffParserTest {

    private StAXTariffParser stAXTariffParser;
    private String xmlFilePath;

    @BeforeClass
    public void setUp() {
        stAXTariffParser = new StAXTariffParser();
        xmlFilePath = "src/main/java/resources/tariff.xml";
    }

    @Test
    public void testParseTariff() throws FileNotFoundException {
        List<Tariff> tariff = stAXTariffParser.parse(xmlFilePath);
        Assert.assertNotNull(tariff, "Parsed tariff list should not be null");
        Assert.assertFalse(tariff.isEmpty(), "Parsed tariff list should not be empty");
        Assert.assertEquals(tariff.size(), 16, "Expected number of tariff should be 16");

        Tariff firstTariff = tariff.get(0);
        Assert.assertEquals(firstTariff.getName(), "Супер1", "First tariff name should be Супер1");
        Assert.assertEquals(firstTariff.getOperatorname(), "A1", "First tariff operator should be A1");
        Assert.assertEquals(firstTariff.getPayroll(), "15p", "15p");
        Assert.assertEquals(firstTariff.getSmsprice(), "16p", "16p");
        Assert.assertEquals(firstTariff.getCallPrice().getPrice("home"), "30 р", "30 р");

    }

    @Test
    public void testParseInvalidFile() {
        String invalidXmlFilePath = "invalid_tariffs.xml";
        Assert.assertThrows(FileNotFoundException.class, () -> stAXTariffParser.parse(invalidXmlFilePath));
    }
}