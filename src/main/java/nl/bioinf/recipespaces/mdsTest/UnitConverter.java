package nl.bioinf.recipespaces.mdsTest;

import java.util.*;

public class UnitConverter {

    static Map<List<String>, Double> volumeUnits = new HashMap<>();
    static Map<String[], Double> weightUnits = new HashMap<>();

    static {
        // Weights to mL
        volumeUnits.put(Arrays.asList("teaspoon", "tsp.", "t."), 5.0);
        volumeUnits.put(Arrays.asList("tablespoon", "tbsp.", "T."), 15.0);
        volumeUnits.put(Arrays.asList("cup", "c.", "cups"), 250.0);
        volumeUnits.put(Arrays.asList("pint", "pt."), 500.0);
        volumeUnits.put(Arrays.asList("quart", "qt."), 950.0);
        volumeUnits.put(Arrays.asList("gallon", "gal."), 3800.0);
        volumeUnits.put(Arrays.asList("peck", "pk"), 8800.0);
    }

    static {
        weightUnits.put(new String[]{"ounce", "ounces", "oz."}, 28.0);
        weightUnits.put(new String[]{"cup", "C."}, 225.0);
        weightUnits.put(new String[]{"pint"}, 450.0);
        weightUnits.put(new String[]{"pound", "lb.", "#"}, 453.0);
    }

    public static String convertUnit(String unitString, Double unitValue){
        double convertedValue = 0;
        unitString = unitString.toLowerCase();
        for(List<String> unitNames : volumeUnits.keySet()){
            if(unitNames.contains(unitString)){
                convertedValue = volumeUnits.get(unitNames);
            }
        }
        return (convertedValue * unitValue) + " mL";
    }
}
