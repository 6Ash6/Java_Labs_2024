package builder;

import entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TariffBuilder {
    protected Tariff tariff;

    public TariffBuilder() {
        tariff = new Tariff();
    }

    public TariffBuilder id(String id) {
        tariff.setId(id);
        return this;
    }

    public TariffBuilder name(String name) {
        tariff.setName(name);
        return this;
    }

    public TariffBuilder operatorName(String operatorName) {
        tariff.setOperatorName(operatorName);
        return this;
    }

    public TariffBuilder payroll(BigDecimal payroll) {
        tariff.setPayroll(payroll);
        return this;
    }

    public TariffBuilder callPrices(List<CallPrice> callPrices) {
        tariff.setCallPrices(callPrices);
        return this;
    }

    public TariffBuilder smsPrice(BigDecimal smsPrice) {
        tariff.setSmsPrice(smsPrice);
        return this;
    }

    public TariffBuilder parameters(List<Parameter> parameters) {
        tariff.setParameters(parameters);
        return this;
    }

    public TariffBuilder date(LocalDate date) {
        tariff.setDate(date);
        return this;
    }

    public Tariff build() {
        return tariff;
    }
}
