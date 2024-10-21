package validator;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class TariffXMLValidator {
    private static final Logger logger = LogManager.getLogger();

    public boolean validate() throws CustomException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = "tariffs.xml"; // Имя файла XML с тарифами
        String schemaName = "tariffs.xsd"; // Имя схемы XSD для тарифов
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
        } catch (SAXException | IOException e) {
            logger.error(fileName + " is not correct or valid", e);
            return false;
        }
        return true;
    }
}
