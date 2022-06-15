package nl.bioinf.recipespaces.model;

import java.util.List;

public class MdsData {

    private double[][] matrix;
    private List<String> ingredientNames;

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public List<String> getIngredientNames() {
        return ingredientNames;
    }

    public void setIngredientNames(List<String> ingredientNames) {
        this.ingredientNames = ingredientNames;
    }

    public MdsData(double[][] matrix, List<String> ingredientNames) {
        this.matrix = matrix;
        this.ingredientNames = ingredientNames;
    }
}
