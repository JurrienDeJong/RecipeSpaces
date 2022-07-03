package nl.bioinf.recipespaces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import nl.bioinf.recipespaces.helperClasses.UnitConverter;
import org.junit.jupiter.api.Test;

/**
 * author: Jurriën de Jong
 */
public class UnitTest {

    @Test
    void testCupUnitToML() {
        String unitString = "C.";
        Double unitValue = 1.5;
        String convertedString = UnitConverter.convertUnit(unitString, unitValue);
        String expected = "375 mL";
        assertEquals(expected, convertedString);
    }

    @Test
    void testPintToKg() {
        String unitString = "pints";
        Double unitValue = 5.5;
        String convertedString = UnitConverter.convertUnit(unitString, unitValue);
        String expected = "2,475 kg";
        assertEquals(expected, convertedString);
    }
}
