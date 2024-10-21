package builder;

import enitity.*;

import java.math.BigDecimal;
import java.util.List;

    public class TariffBuilder {
        protected Tariff tariff;

        public TariffBuilder() {
            tariff = new Tariff();
        }


        public builder.TariffBuilder name(String name) {
            tariff.setName(name);
            return this;
        }

        public builder.TariffBuilder operatorName(String operatorName) {
            tariff.setOperatorName(Operator.valueOf(operatorName));
            return this;
        }

        public builder.TariffBuilder payroll(BigDecimal payroll) {
            tariff.setPayroll(payroll);
            return this;
        }

        public builder.TariffBuilder callPrices(List<CallPrice> callPrices) {
            tariff.setCallPrices(callPrices);
            return this;
        }

        public builder.TariffBuilder smsPrice(BigDecimal smsPrice) {
            tariff.setSmsPrice(smsPrice);
            return this;
        }

        public builder.TariffBuilder parameters(List<Parameter> parameters) {
            tariff.setParameters(parameters);
            return this;
        }


        public Tariff build() {
            return tariff;
        }
    }

