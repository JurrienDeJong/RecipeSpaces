package nl.bioinf.recipespaces.model;

import java.util.List;

/**
 * POJO for ingredientData uploaded by users
 * @author Jurriën de Jong
 */

public class IngredientData {

    private List<Ingredient> ingredients;
    private List<Molecule> molecules;

    public IngredientData() {

    }

    public IngredientData(List<Ingredient> ingredients, List<Molecule> molecules) {
        this.ingredients = ingredients;
        this.molecules = molecules;
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
