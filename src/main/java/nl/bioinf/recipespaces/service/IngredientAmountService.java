package nl.bioinf.recipespaces.service;

import nl.bioinf.recipespaces.model.IngredientAmount;
import nl.bioinf.recipespaces.dao.IngredientAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
/**
 * Connects controller actions to the correct queries in IngredientAmountRepository
 * @author Rose Hazenberg
 */
@Service
public class IngredientAmountService {

    private final IngredientAmountRepository repository;

    @Autowired
    public IngredientAmountService(IngredientAmountRepository repository) {
        this.repository = repository;
    }

    public List<IngredientAmount> getAllIds() {
        return this.repository.findAll();
    }

    public Optional<IngredientAmount> getId(String id) {
        return this.repository.findById(id);
    }

    public IngredientAmount getIngredientAmountFromRecipe(Integer recipeID) {
        return this.repository.ingredientAmountFromRecipe(recipeID);
    }

    public List<IngredientAmount> getIngredientAmountsFromRecipe(Integer id) {
        return this.repository.ingredientAmountsFromRecipe(id);
    }
}
