package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.logging.HtmlLogFormatter;
import nl.bioinf.recipespaces.model.*;
import nl.bioinf.recipespaces.service.IngredientAmountService;
import nl.bioinf.recipespaces.service.RecipeService;
import nl.bioinf.recipespaces.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;

/**
 * Handles recipe view
 * @author Jurriën de Jong
 */
@Controller()
@RequestMapping(path="/recipe")
public class RecipeController {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RecipeController.class.getName());

    private final RecipeService recipeService;
    private final StepService stepService;
    private final IngredientAmountService ingredientAmountService;

    @Autowired
    public RecipeController(RecipeService recipeService, StepService stepService, IngredientAmountService ingredientAmountService) {
        this.recipeService = recipeService;
        this.stepService = stepService;
        this.ingredientAmountService = ingredientAmountService;
    }

    /**
     * Get recipe from ID
     * @param model, to add attributes to use in html
     * @param id, integer with the id of a recipe
     * @return html page with a recipe
     */
    @GetMapping("/{id}")
    public String displayRecipeByID(Model model, @PathVariable("id") Integer id) throws IOException {
        logger.log(Level.INFO, "Retrieving recipe with id: " + id + " without warnings");

        Recipe recipe = recipeService.getId(id);
        List<Ingredient> ingredients = recipeService.getIngredientsFromRecipe(id);
        Set<Step> steps = stepService.getStepsFromRecipe(id);
        List<IngredientAmount> ingredientAmounts = ingredientAmountService.getIngredientAmountsFromRecipe(id);
        List<Map<String, Integer>> ingredientFrequency = ingredientAmountService.getCountIngredientForRecipe(recipe.getTagValue());
        List<Recipe> recipes = recipeService.findByExactKeyword(recipe.getTagValue());
        try{
            model.addAttribute("recipeName", recipe.getTagValue());
            model.addAttribute("recipeId", recipe.getId());
            model.addAttribute("recipes", recipe);
            model.addAttribute("ingredients", ingredients);
            model.addAttribute("steps", steps);
            model.addAttribute("ingredient_amount", ingredientAmounts);
            model.addAttribute("ing_frequency", ingredientFrequency);
            model.addAttribute("recipesExact", recipes);
            logger.log(Level.INFO, "Adding recipe data to the model");
        } catch (Exception e){
            logger.log(Level.SEVERE, "Something went wrong; cause= " + e.getCause() + ", message= " + e.getMessage());
            System.out.println(e.getMessage());
        }

        FileHandler fileHandler = new FileHandler("logfile.html", false);
        fileHandler.setFormatter(new HtmlLogFormatter());
        logger.addHandler(fileHandler);

        return "recipe";
    }

    /**
     * Get all recipes with the same name
     * @param model, to add attributes to use in html
     * @param tagValue, the name of a recipe
     * @return html page with multiple recipe with the same name
     */
    @GetMapping("/multiple/{tagValue}")
    public String displayRecipesWithSameTagValue(Model model, @PathVariable("tagValue") String tagValue) {
        logger.log(Level.INFO, "Retrieving " + tagValue + " which contains multiple recipes without warnings");

        Map<Integer, List<Ingredient>> recipeData = new HashMap<>();
        List<Recipe> recipes = recipeService.findByExactKeyword(tagValue);
        for (Recipe recipe : recipes){
            recipeData.put(recipe.getId(), recipeService.getIngredientsFromRecipe(recipe.getId()));
        }

        try{
            model.addAttribute("recipeCount", recipeData.values().size());
            model.addAttribute("recipeName", recipes.get(0).getTagValue());
            model.addAttribute("recipeData", recipeData);
        } catch (Exception e){
            logger.log(Level.SEVERE, "Something went wrong; cause= " + e.getCause() + ", message= " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return "multipleRecipes";
    }
}
