package tariff;

public class Tariff {
    private String id;
    private String name;
    private String operatorname;
    private String payroll;
    private String smsprice;
    private CallPrice callPrice;
    private Parameters parameters;


    public Tariff(String id, String name, String operatorname, String payroll,String smsprice, CallPrice callPrice, Parameters parameters) {
        this.id = id;
        this.name = name;
        this.operatorname = operatorname;
        this.payroll = payroll;
        this.smsprice = smsprice;
        this.callPrice = callPrice;
        this.parameters = parameters;
    }

    public CallPrice getCallPrice() {
        return callPrice;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", operatorname='" + operatorname + '\'' +
                ", payroll='" + payroll + '\'' +
                ", smsprice=" + smsprice +
                ", callPrice=" + callPrice +
                ", parameters='" + parameters + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname;
    }

    public void setPayroll(String payroll) {
        this.payroll = payroll;
    }

    public void setSmsPrice(String smsprice) {
        this.smsprice = smsprice;
    }

    public void setCallPrice(CallPrice callPrice) {
        this.callPrice = callPrice;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOperatorname() {
        return operatorname;
    }

    public String getPayroll() {
        return payroll;
    }

    public String getSmsprice() {
        return smsprice;
    }
}
