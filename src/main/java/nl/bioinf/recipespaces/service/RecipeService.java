package nl.bioinf.recipespaces.service;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.dao.IngredientRepository;
import nl.bioinf.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.dao.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Integer missingId(String keyword) { return this.recipeRepository.missingId(keyword);}

    public Recipe getId(Integer id) {
        return this.recipeRepository.findRecipeById(id);
    }

    public List<Recipe> findByKeyword(String keyword) { return this.recipeRepository.recipesFromKeyword(keyword);}

    public Set<Ingredient> getIngredientsFromRecipe(Integer id) {
        return this.ingredientRepository.ingredientsFromRecipe(id);
    }

    public Set<Recipe> getRecipesFromIngredient(Integer id) {
        return this.recipeRepository.recipesFromIngredient(id);
    }

}
