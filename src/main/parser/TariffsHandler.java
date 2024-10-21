package parser;

import entity.*;
import exception.CustomException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static by.epam.anhelinam.task2.parser.TariffXmlTag.*;

public class TariffsHandler extends DefaultHandler {
    private Set<Tariff> tariffs;
    private Tariff currentTariff;
    private Tariff.CallPrices currentCallPrices;
    private Tariff.Parameters currentParameters;
    private TariffXmlTag currentXmlTag;
    private EnumSet<TariffXmlTag> withText;

    public TariffsHandler() {
        tariffs = new HashSet<>();
        withText = EnumSet.range(TariffXmlTag.NAME, TariffXmlTag.CONNECTION_FEE);
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        switch (TariffXmlTag.valueOf(qName.toUpperCase())) {
            case TARIFF:
                currentTariff = new Tariff();
                currentTariff.setId(attrs.getValue(0));
                break;
            case CALL_PRICES:
                currentCallPrices = new Tariff.CallPrices();
                currentTariff.setCallPrices(currentCallPrices);
                break;
            case PARAMETERS:
                currentParameters = new Tariff.Parameters();
                currentTariff.setParameters(currentParameters);
                break;
            default:
                TariffXmlTag temp = TariffXmlTag.valueOf(qName.toUpperCase());
                if (withText.contains(temp)) {
                    currentXmlTag = temp;
                }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (TARIFF.getValue().equals(qName)) {
            tariffs.add(currentTariff);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            try {
                switch (currentXmlTag) {
                    case NAME:
                        currentTariff.setName(data);
                        break;
                    case OPERATOR_NAME:
                        currentTariff.setOperatorName(data);
                        break;
                    case PAYROLL:
                        currentTariff.setPayroll(Double.parseDouble(data));
                        break;
                    case SMS_PRICE:
                        currentTariff.setSmsPrice(Double.parseDouble(data));
                        break;
                    case INSIDE_NETWORK:
                        currentCallPrices.setInsideNetwork(Double.parseDouble(data));
                        break;
                    case OUTSIDE_NETWORK:
                        currentCallPrices.setOutsideNetwork(Double.parseDouble(data));
                        break;
                    case STATIONARY_PHONE:
                        currentCallPrices.setStationaryPhone(Double.parseDouble(data));
                        break;
                    case FAVORITE_NUMBER:
                        currentParameters.setFavoriteNumber(Integer.parseInt(data));
                        break;
                    case TARIFICATION:
                        currentParameters.setTariffication(data);
                        break;
                    case CONNECTION_FEE:
                        currentParameters.setConnectionFee(Double.parseDouble(data));
                        break;
                    default:
                        throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.name());
                }
            } catch (CustomException e) {
                e.printStackTrace();
            }
        }
        currentXmlTag = null;
    }
}
