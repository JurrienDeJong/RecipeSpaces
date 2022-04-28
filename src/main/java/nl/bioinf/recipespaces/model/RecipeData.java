package nl.bioinf.recipespaces.model;

import java.util.List;

public class RecipeData {

    private String recipeName;
    private String steps;

    public RecipeData() {
    }

    public RecipeData(String recipeName, String steps) {
        this.recipeName = recipeName;
        this.steps = steps;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
