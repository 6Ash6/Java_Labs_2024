package parser;

import entity.Tariff;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTariffBuilder {
    protected Set<Tariff> tariffs;

    public AbstractTariffBuilder() {
        tariffs = new HashSet<>();
    }

    public AbstractTariffBuilder(Set<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    public abstract void buildSetTariffs(String filename);
}
