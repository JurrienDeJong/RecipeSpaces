package nl.bioinf.recipespaces.model;

import java.util.List;

/**
 * POJO for MDS input data
 * @author Jurriën de Jong
 */


public class MdsData {

    private double[][] matrix;
    private List<String> ingredientNames;

    public double[][] getMatrix() {
        return matrix;
    }

    public List<String> getIngredientNames() {
        return ingredientNames;
    }

    public MdsData(double[][] matrix, List<String> ingredientNames) {
        this.matrix = matrix;
        this.ingredientNames = ingredientNames;
    }
}
