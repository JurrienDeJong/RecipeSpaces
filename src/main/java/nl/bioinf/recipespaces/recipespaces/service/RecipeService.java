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

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllIds() {
        return this.recipeRepository.findAll();
    }

    public Optional<Recipe> getId(String id) {
        return this.recipeRepository.findById(id);
    }

    public Set<Recipe> getRecipesFromIngredient(String tagValue) { return this.recipeRepository.recipesFromIngredient(tagValue);}
}
