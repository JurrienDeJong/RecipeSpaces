package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.model.*;
import nl.bioinf.recipespaces.service.IngredientAmountService;
import nl.bioinf.recipespaces.service.RecipeService;
import nl.bioinf.recipespaces.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

/**
 * Handles recipe view
 * @author Jurriën de Jong
 */
@Controller()
public class RecipeController {

    @Autowired
    private final RecipeService recipeService;
    private final StepService stepService;
    private final IngredientAmountService ingredientAmountService;

    @Autowired
    public RecipeController(RecipeService recipeService, StepService stepService, IngredientAmountService ingredientAmountService) {
        this.recipeService = recipeService;
        this.stepService = stepService;
        this.ingredientAmountService = ingredientAmountService;
    }

    // Get all recipes
    @GetMapping("/recipe")
    public String displayRecipes(Model model){
        Set<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "allrecipes";
    }

/*    @GetMapping
    public List<Recipe> getXAmountOfRecipes(int amount) {
        return this.recipeService.getXAmountOfRecipes(amount);
    }*/


    // Get Recipe from ID
    @GetMapping("/recipe/{id}")
    public String displayRecipeByID(Model model, @PathVariable("id") Integer id){
        Recipe recipe = recipeService.getId(id);
        Set<Ingredient> ingredients = recipeService.getIngredientsFromRecipe(id);
        Set<Step> steps = stepService.getStepsFromRecipe(id);
        List<IngredientAmount> ingredientAmounts = ingredientAmountService.getIngredientAmountsFromRecipe(id);
        try{
            model.addAttribute("recipeName", recipe.getTagValue());
            model.addAttribute("recipes", recipe);
            model.addAttribute("ingredients", ingredients);
            model.addAttribute("steps", steps);
            model.addAttribute("ingredient_amount", ingredientAmounts);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "recipe";
    }

    // Get Recipe from ID
    @GetMapping("/recipe/multiple/{tagValue}")
    public String displayRecipesWithSameTagValue(Model model, @PathVariable("tagValue") String tagValue){
        Map<Integer, List<IngredientAmount>> recipeData = new HashMap<>();
        List<Recipe> recipes = recipeService.findByExactKeyword(tagValue);
        for (Recipe recipe : recipes){
            recipeData.put(recipe.getId(),recipeService.getIngredientAmountsFromRecipe(recipe.getId()));
        }

        try{
            model.addAttribute("recipeCount", recipeData.values().size());
            model.addAttribute("recipeName", recipes.get(0).getTagValue());
            model.addAttribute("recipeData", recipeData);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "multipleRecipes";
    }

    @GetMapping("search/{recipeID}")
    @ResponseBody
    public Set<Ingredient> getIngredients(@PathVariable("recipeID") Integer id) throws ResponseStatusException {
        try{
            return this.recipeService.getIngredientsFromRecipe(id);
        } catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }


}
