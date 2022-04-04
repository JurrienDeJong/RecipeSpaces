package nl.bioinf.recipespaces.recipespaces.service;

import nl.bioinf.recipespaces.recipespaces.model.IngredientAmount;
import nl.bioinf.recipespaces.recipespaces.model.IngredientAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public Set<IngredientAmount> getIngredientAmountFromRecipe(Integer recipeID) {
        return this.repository.ingredientAmountFromRecipe(recipeID);
    }
}