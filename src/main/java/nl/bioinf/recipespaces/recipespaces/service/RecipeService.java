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

    public List<Recipe> getAllIds() {
        return this.recipeRepository.findAll();
    }

    public Optional<Recipe> getId(String id) {
        return this.recipeRepository.findById(id);
    }


    public List<Ingredient> getIngredientsFromRecipe(String id) throws Exception {
        Optional<Ingredient> result = this.ingredientRepository.findById(id);
        Ingredient ingredient = result.orElseThrow( () -> new Exception() );
        return ingredient.getIngredients();
    }
}
