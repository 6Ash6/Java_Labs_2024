package test;

import Exception.CustomException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidatorTest {
    @Test
    public void testValidate() throws CustomException {
        TariffXMLValidator validator = new TariffXMLValidator();
        boolean isValid = validator.validate("valid.xml"); // передаем путь к вашему XML файлу
        Assert.assertTrue(isValid);
    }
}
