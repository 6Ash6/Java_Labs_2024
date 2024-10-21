package tariff;

import java.util.HashMap;
import java.util.Map;

public class Parameters {
    private Map<String, String> parameters;

    public Parameters() {
        this.parameters = new HashMap<>();
    }

    public void addParam(String type, String value) {
        this.parameters.put(type, value);
    }

    public String getParam(String type) {
        return parameters.get(type);
    }

    public Map<String, String> getParam() {
        return parameters;
    }

    @Override
    public String toString() {
        return "Parameters{" + "parameters=" + parameters + '}';
    }
}
