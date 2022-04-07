package nl.bioinf.recipespaces.service;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.dao.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
/**
 * Connects controller actions to the correct queries in IngredientRepository
 * @author Jurriën de Jong
 */
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

    public Ingredient getId(Integer id) {
        return repository.findIngredientById(id);
    }

    public Set<Ingredient> getIngredientsFromRecipe(Integer recipeID) {
        return repository.ingredientsFromRecipe(recipeID);
    }

    public Integer missingId(String keyword) { return this.repository.missingId(keyword);}

    public List<Ingredient> findByKeyword(String keyword) { return this.repository.ingredientFromKeyword(keyword);}




}
