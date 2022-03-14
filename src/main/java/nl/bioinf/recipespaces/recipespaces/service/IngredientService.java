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
public class IngredientService {

    private final IngredientRepository repository;

    @Autowired
    public IngredientService(IngredientRepository repository) {
        this.repository = repository;
    }

    public List<Ingredient> getAllIds() {
        return repository.findAll();
    }

    public Ingredient getId(String id) {
        return repository.findIngredientById(id);
    }

    public Set<Ingredient> getIngredientsFromRecipe(String recipeID) {
        return repository.ingredientsFromRecipe(recipeID);
    }

    public String missingId(String keyword) { return this.repository.missingId(keyword);}

    public List<Ingredient> findByKeyword(String keyword) { return this.repository.ingredientFromKeyword(keyword);}
}
