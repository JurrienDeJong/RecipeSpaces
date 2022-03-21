package nl.bioinf.recipespaces.recipespaces.service;

import nl.bioinf.recipespaces.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.recipespaces.model.IngredientRepository;
import nl.bioinf.recipespaces.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.recipespaces.model.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Set<Recipe> getAllRecipes() {
        return this.recipeRepository.selectAllRecipes();
    }

    public List<Recipe> getAllIds() {
        return this.recipeRepository.findAll();
    }

    public String missingId(String keyword) { return this.recipeRepository.missingId(keyword);}

    public Recipe getId(String id) {
        return this.recipeRepository.findRecipeById(id);
    }

    public List<Recipe> findByKeyword(String keyword) { return this.recipeRepository.recipesFromKeyword(keyword);}

    public Set<Ingredient> getIngredientsFromRecipe(String id) {
        return this.ingredientRepository.ingredientsFromRecipe(id);
    }

    public Set<Recipe> getRecipesFromIngredient(String id) {
        return this.recipeRepository.recipesFromIngredient(id);
    }

}
