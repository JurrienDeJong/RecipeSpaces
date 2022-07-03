package nl.bioinf.recipespaces.model;

import java.util.List;

/**
 * POJO for the unitconverter
 * @author Jurriën de Jong
 */

public class Unit {

    private List<String> unitNames;
    private UnitType unitType;
    private Double unitValue;

    public Unit(List<String> unitNames, UnitType unitType, Double unitValue) {
        this.unitNames = unitNames;
        this.unitType = unitType;
        this.unitValue = unitValue;
    }

    public List<String> getUnitNames() {
        return unitNames;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public Double getUnitValue() {
        return unitValue;
    }
}
