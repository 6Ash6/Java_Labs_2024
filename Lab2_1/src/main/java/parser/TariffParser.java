package parser;

import tariff.Tariff;

import java.io.FileNotFoundException;
import java.util.List;

public interface TariffParser {
    List<Tariff> parse(String filePath) throws FileNotFoundException;
}