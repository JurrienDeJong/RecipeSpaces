package nl.bioinf.recipespaces.MDS;

import nl.bioinf.recipespaces.model.Unit;
import nl.bioinf.recipespaces.model.UnitType;

import java.text.DecimalFormat;
import java.util.*;

public class UnitConverter {

    static List<Unit> units = new ArrayList<>();

    static String[] massUnits = {" g", " kg"};
    static String[] volumeUnits = {" mL", " L"};

    static {
        // Volumes to mL
        units.add(new Unit(Arrays.asList("teaspoons", "teaspoon", "tsp.", "t."), UnitType.VOLUME, 5.0));
        units.add(new Unit(Arrays.asList("tablespoons", "tablespoon", "tbsp.", "T."), UnitType.VOLUME, 15.0));
        units.add(new Unit(Arrays.asList("cup", "c.", "cups"), UnitType.VOLUME, 250.0));
        units.add(new Unit(Arrays.asList("pint", "pt."), UnitType.VOLUME, 500.0));
        units.add(new Unit(Arrays.asList("quart", "qt."), UnitType.VOLUME, 950.0));
        units.add(new Unit(Arrays.asList("gallon", "gal."), UnitType.VOLUME, 3800.0));
        units.add(new Unit(Arrays.asList("teaspoons", "teaspoon", "tsp.", "t."), UnitType.VOLUME, 5.0));
        units.add(new Unit(Arrays.asList("peck", "pk"), UnitType.VOLUME, 8800.0));

        // Weights to g
        units.add(new Unit(Arrays.asList("ounce", "ounces", "oz."), UnitType.WEIGHT, 28.0));
        units.add(new Unit(Arrays.asList("cup", "C.", "cups"), UnitType.WEIGHT, 225.0));
        units.add(new Unit(Arrays.asList("pint", "pints"), UnitType.WEIGHT, 450.0));
        units.add(new Unit(Arrays.asList("pound", "lb.", "#"), UnitType.WEIGHT,453.0));
    }

    public static String convertUnit(String unitString, Double unitValue){
        int unitIndex = 0;
        double convertedValue = 0;
        String endsWith = "";
        unitString = unitString.toLowerCase();
        for(Unit unit : units){
            if(unit.getUnitNames().contains(unitString)){
                convertedValue = unitValue * unit.getUnitValue();

                if(convertedValue >= 1000){
                    convertedValue /= 1000;
                    unitIndex += 1;
                }

                switch (unit.getUnitType()){
                    case VOLUME -> endsWith = volumeUnits[unitIndex];
                    case WEIGHT -> endsWith = massUnits[unitIndex];
                }

                break;
            } else {
                convertedValue = unitValue;
            }
        }

        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(convertedValue) + endsWith;
    }
}
