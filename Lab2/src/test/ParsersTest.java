package test;

import builder.TariffBuilder;
import enitity.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class ParsersTest {
    private Set<Tariff> expectedSet;
    private String FILE_PATH_VALID = "valid.xml";

    @BeforeClass
    public void setUp() {
        expectedSet = new HashSet<>();

        Tariff tariff1 = new TariffBuilder()
                .name("Basic Plan")
                .operator("Operator A")
                .monthlyFee(10.0)
                .callRate(0.1)
                .smsRate(0.05)
                .parameters("Unlimited calls within the same network.")
                .build();

        Tariff tariff2 = new TariffBuilder()
                .name("Premium Plan")
                .operator("Operator B")
                .monthlyFee(20.0)
                .callRate(0.05)
                .smsRate(0.02)
                .parameters("Unlimited calls and texts.")
                .build();

        expectedSet.add(tariff1);
        expectedSet.add(tariff2);
    }

    @Test
    public void DOMBuildSetTariffsPositiveTest() {
        TariffsDomBuilder builder = new TariffsDomBuilder();
        builder.buildSetTariffs(FILE_PATH_VALID);
        Set<Tariff> actualSet = builder.getTariffs();
        Assert.assertEquals(actualSet, expectedSet);
    }

    @Test
    public void SAXBuildSetTariffsPositiveTest() {
        TariffsSaxBuilder builder = new TariffsSaxBuilder();
        builder.buildSetTariffs(FILE_PATH_VALID);
        Set<Tariff> actualSet = builder.getTariffs();
        Assert.assertEquals(actualSet, expectedSet);
    }

    @Test
    public void StAXBuildSetTariffsPositiveTest() {
        TariffsStaxBuilder builder = new TariffsStaxBuilder();
        builder.buildSetTariffs(FILE_PATH_VALID);
        Set<Tariff> actualSet = builder.getTariffs();
        Assert.assertEquals(actualSet, expectedSet);
    }
}
