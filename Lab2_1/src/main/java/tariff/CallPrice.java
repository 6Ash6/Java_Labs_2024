package tariff;

import java.util.HashMap;
import java.util.Map;

public class CallPrice {
    private Map<String, String> prices = new HashMap<>();

    public CallPrice() {
        this.prices = new HashMap<>();
    }

    public void addPrice(String type, String value) {
        this.prices.put(type, value);
    }

    public String getPrice(String type) {
        return prices.get(type);
    }

    public Map<String, String> getPrice() {
        return prices;
    }

    @Override
    public String toString() {
        return "CallPrices{" +
                "prices=" + prices +
                '}';
    }
}