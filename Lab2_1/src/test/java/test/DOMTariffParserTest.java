package test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import parser.DOMTariffParser;
import tariff.Tariff;

import java.io.FileNotFoundException;
import java.util.List;

public class DOMTariffParserTest {

    private DOMTariffParser domTariffParser;
    private String xmlFilePath;

    @BeforeClass
    public void setUp() {
        domTariffParser = new DOMTariffParser();
        xmlFilePath = "D:\\Java\\Lab2_1\\src\\main\\java\\resources\\tariff.xml";
    }

    @Test
    public void testParseTariff() throws FileNotFoundException {
        List<Tariff> tariffs = domTariffParser.parse(xmlFilePath);
        Assert.assertNotNull(tariffs, "Parsed tariffs list should not be null");
        Assert.assertFalse(tariffs.isEmpty(), "Parsed tariffs list should not be empty");
        Assert.assertEquals(tariffs.size(), 16, "Expected number of tariffs should be 16");

        Tariff firstTariff = tariffs.get(0);
        Assert.assertEquals(firstTariff.getName(), "Супер1", "First tariff name should be Супер1");
        Assert.assertEquals(firstTariff.getOperatorname(), "A1", "First tariff operator should be A1");
        Assert.assertEquals(firstTariff.getPayroll(), "15p", "15p");
        Assert.assertEquals(firstTariff.getSmsprice(), "16p", "16p");


    }

    @Test
    public void testParseInvalidFile() {
        String invalidXmlFilePath = "invalid_tariffs.xml";
        Assert.assertThrows(FileNotFoundException.class, () -> domTariffParser.parse(invalidXmlFilePath));
    }
}