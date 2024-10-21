package parser;

import org.xml.sax.ErrorHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXParseException;

public class TariffErrorHandler implements ErrorHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void warning(SAXParseException e) {
        LOGGER.warn(getExceptionLocation(e) + " - " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) {
        LOGGER.error(getExceptionLocation(e) + " - " + e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) {
        LOGGER.fatal(getExceptionLocation(e) + " - " + e.getMessage());
    }

    private String getExceptionLocation(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
