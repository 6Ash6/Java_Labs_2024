package entity;

import java.math.BigDecimal;
import java.util.List;

public class Tariff {
    private String name;
    private Operator operatorName;
    private BigDecimal payroll;
    private List<CallPrice> callPrices;
    private BigDecimal smsPrice;
    private List<ParameterDetail> parameters;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Operator getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(Operator operatorName) {
        this.operatorName = operatorName;
    }

    public BigDecimal getPayroll() {
        return payroll;
    }

    public void setPayroll(BigDecimal payroll) {
        this.payroll = payroll;
    }

    public List<CallPrice> getCallPrices() {
        return callPrices;
    }

    public void setCallPrices(List<CallPrice> callPrices) {
        this.callPrices = callPrices;
    }

    public BigDecimal getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(BigDecimal smsPrice) {
        this.smsPrice = smsPrice;
    }

    public List<ParameterDetail> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterDetail> parameters) {
        this.parameters = parameters;
    }
}
