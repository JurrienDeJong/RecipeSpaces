package nl.bioinf.recipespaces.service;

import nl.bioinf.recipespaces.dao.DistanceRepository;
import nl.bioinf.recipespaces.dao.IngredientRepository;
import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.IngredientDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Connects controller actions to the correct queries in DistanceRepo
 * @author Jurriën de Jong
 */

@Service
public class DistanceService {

    private final DistanceRepository distanceRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public DistanceService(DistanceRepository distanceRepository, IngredientRepository ingredientRepository) {
        this.distanceRepository = distanceRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<IngredientDistance> getDistancesFromId(Integer id){
        return distanceRepository.ingredientDistancesFromId(id);
    }

    public Optional<Double> getDistanceFromPair(Integer id, Integer target){
        return distanceRepository.getDistanceFromPair(id, target);
    }

    public List<Ingredient> getIngredientsContainingDistance(Integer recipeId){
        return ingredientRepository.getIngredientsFromRecipeWithDistanceData(recipeId);
    }


    public List<IngredientDistance> compareIngredientToAll(Integer id){
        return distanceRepository.compareIngredientAgainstAll(id);
    }
}
