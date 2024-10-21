package entity;

import java.math.BigDecimal;

public class CallPrice {
    private CallPriceType type;
    private BigDecimal price;

    // Getters and Setters

    public CallPriceType getType() {
        return type;
    }

    public void setType(CallPriceType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
