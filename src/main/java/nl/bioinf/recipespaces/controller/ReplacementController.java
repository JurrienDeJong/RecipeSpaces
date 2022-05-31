package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.ReplacementData;
import nl.bioinf.recipespaces.service.IngredientAmountService;
import nl.bioinf.recipespaces.service.IngredientService;
import nl.bioinf.recipespaces.service.MoleculeService;
import nl.bioinf.recipespaces.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Controller
public class ReplacementController {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ReplacementController.class.getName());

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final MoleculeService moleculeService;
    private final IngredientAmountService ingredientAmountService;

    @Autowired
    public ReplacementController(RecipeService recipeService,
                                 IngredientService ingredientService,
                                 MoleculeService moleculeService,
                                 IngredientAmountService ingredientAmountService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.moleculeService = moleculeService;
        this.ingredientAmountService = ingredientAmountService;
    }

    @RequestMapping(value = "recipe/replacement/search", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Double> getReplacement(@RequestParam(value = "term", defaultValue = "") String term) {
        logger.log(Level.INFO, "Fetching " + term + " to get its replacements based on similar ingredients");

        Ingredient ingredient = ingredientService.findByExactKeyword(term);
        Map<String, Double> scores = new HashMap<>();
        List<Integer> IdsOfIngredients = ingredientService.getAllIdsContainingMolecules();
        for(Integer ingredientId : IdsOfIngredients){
            logger.log(Level.INFO, "Finding matches to replace " + term);
            Set<String> current = moleculeService.getCommonNames(ingredient.getId());
            Set<String> target = moleculeService.getCommonNames(ingredientId);
            double matches;
            double total;
            if(target.size() > current.size()){
                current.retainAll(target);
                matches = Double.parseDouble(String.valueOf(current.size()));
                total = Double.parseDouble(String.valueOf(target.size()));
            } else {
                target.retainAll(current);
                matches = Double.parseDouble(String.valueOf(target.size()));
                total = Double.parseDouble(String.valueOf(current.size()));
            }

            logger.log(Level.INFO, "Calculating percentage of a match");
            double percentage = ((matches / total) * 100);
            if(percentage > 0){
                scores.put(ingredientService.getId(ingredientId).getTagValue(), percentage);
            }
        }

        return scores.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Double>comparingByValue().reversed()))
                // Skip the first element being the ingredient itself with 100 % similarity of course.
                .skip(1)
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }


    @RequestMapping(value = "recipe/{id}/ingredients/{all}", method = RequestMethod.GET)
    @ResponseBody
    public List<ReplacementData> getIngredients(@PathVariable("id") Integer id, @PathVariable("all") Boolean allIng){
        logger.log(Level.INFO, "Getting replacement ingredients");
        logger.log(Level.WARNING, "Retrieving data due to the large processing of all the replacements for a ingredient");
        List<Ingredient> ingredients;
        if(!allIng){
            ingredients = recipeService.getIngredientsFromRecipe(id);
        } else {
            ingredients = ingredientService.getAllIds();
        }
        List<ReplacementData> replacementData = new ArrayList<>();
        for (Ingredient ingredient:
             ingredients) {
            boolean valid = moleculeService.getMoleculesFromIngredient(ingredient.getId()).size() > 0;
            replacementData.add(new ReplacementData(ingredient.getId(), ingredient.getTagValue(), valid));
        }
        return replacementData;
    }
}
