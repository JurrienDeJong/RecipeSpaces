package nl.bioinf.recipespaces.service;

import nl.bioinf.recipespaces.dao.IngredientAmountRepository;
import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.dao.IngredientRepository;
import nl.bioinf.recipespaces.model.IngredientAmount;
import nl.bioinf.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.dao.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
/**
 * Connects controller actions to the correct queries in RecipeRepository
 * @author Jurriën de Jong
 */
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientAmountRepository ingredientAmountRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, IngredientAmountRepository ingredientAmountRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientAmountRepository = ingredientAmountRepository;
    }

    public Set<Recipe> getAllRecipes() {
        return this.recipeRepository.selectAllRecipes();
    }

    public List<Recipe> getAllIds() {
        return this.recipeRepository.findAll();
    }

    public List<Integer> missingId(String keyword) { return this.recipeRepository.missingId(keyword);}

    public Recipe getId(Integer id) {
        return this.recipeRepository.findRecipeById(id);
    }

    public List<Recipe> findByKeyword(String keyword) { return this.recipeRepository.recipesFromKeyword(keyword);}

    public List<Recipe> findByExactKeyword(String keyword) { return this.recipeRepository.recipesFromExactKeyword(keyword);}

    public List<Ingredient> getIngredientsFromRecipe(Integer id) {
        return this.ingredientRepository.ingredientsFromRecipe(id);
    }


    public Set<Recipe> getRecipesFromIngredient(Integer id) {
        return this.recipeRepository.recipesFromIngredient(id);
    }

    public IngredientAmount getIngredientAmountFromRecipe(Integer recipeId) {
        return this.ingredientAmountRepository.ingredientAmountFromRecipe(recipeId);
    }

    public List<IngredientAmount> getIngredientAmountsFromRecipe(Integer recipeId) {
        return this.ingredientAmountRepository.ingredientAmountsFromRecipe(recipeId);
    }
}
