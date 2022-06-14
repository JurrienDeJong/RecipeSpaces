package nl.bioinf.recipespaces;

import nl.bioinf.recipespaces.MDS.UnitConverter;
import org.junit.jupiter.api.Test;

public class UnitTest {

    @Test
    void testCupUnitToML() {
        String unitString = "C.";
        Double unitValue = 1.5;
        String convertedString = UnitConverter.convertUnit(unitString, unitValue);
        String expected = ""
    }
}
