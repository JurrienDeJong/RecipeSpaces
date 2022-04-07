package nl.bioinf.recipespaces.model;

import java.util.List;

/**
 * POJO for ingredientData uploaded by users
 * @author Jurriën de Jong
 */

public class IngredientData {
    private String recipeTag;
    private String step;

    private List<Ingredient> ingredients;
    private List<Molecule> molecules;

    public IngredientData() {

    }

    public IngredientData(List<Ingredient> ingredients, List<Molecule> molecules) {
        this.ingredients = ingredients;
        this.molecules = molecules;
    }

    public String getRecipeTag() {
        return recipeTag;
    }

    public void setRecipeTag(String recipeTag) {
        this.recipeTag = recipeTag;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Molecule> getMolecules() {
        return molecules;
    }

    public void setMolecules(List<Molecule> molecules) {
        this.molecules = molecules;
    }
}
