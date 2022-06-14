package nl.bioinf.recipespaces.model;

import java.util.List;

public class Unit {

    private List<String> unitNames;
    private UnitType unitType;
    private Double unitValue;

    public Unit(List<String> unitNames, UnitType unitType, Double unitValue) {
        this.unitNames = unitNames;
        this.unitType = unitType;
        this.unitValue = unitValue;
    }

    public void setUnitNames(List<String> unitNames) {
        this.unitNames = unitNames;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public void setUnitValue(Double unitValue) {
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
