package nl.bioinf.recipespaces.service;

import nl.bioinf.recipespaces.dao.ReplacementRepository;
import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.dao.IngredientRepository;
import nl.bioinf.recipespaces.model.ReplacementData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * Connects controller actions to the correct queries in IngredientRepository
 * @author Jurriën de Jong
 */
@Service
public class IngredientService {

    private final IngredientRepository repository;
    private final ReplacementRepository replacementRepository;

    @Autowired
    public IngredientService(IngredientRepository repository, ReplacementRepository replacementRepository) {
        this.repository = repository;
        this.replacementRepository = replacementRepository;
    }

    public List<Ingredient> getAllIds() {
        return repository.findAll();
    }

    public Ingredient getId(Integer id) {
        return repository.findIngredientById(id);
    }

    public List<Ingredient> getIngredientsFromRecipe(Integer recipeID) {
        return repository.ingredientsFromRecipe(recipeID);
    }

    public Integer missingId(String keyword) { return this.repository.missingId(keyword);}

    public List<Ingredient> findByKeyword(String keyword) { return this.repository.ingredientFromKeyword(keyword);}

    public Ingredient findByExactKeyword(String keyword) { return this.repository.ingredientFromExactKeyword(keyword);}

    public List<Ingredient> findXByKeyword(Integer amount) { return this.repository.ingredientXAmount(amount);}

    public List<Map<String, String>> getReplacementData() { return this.replacementRepository.getReplacementData();
    }

    public List<Integer> getAllIdsContainingMolecules() { return this.replacementRepository.getIdsContainingMolecules();
    }
}
