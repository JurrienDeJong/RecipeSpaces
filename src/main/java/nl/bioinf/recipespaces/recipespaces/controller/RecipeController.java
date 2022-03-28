package nl.bioinf.recipespaces.recipespaces.controller;

import nl.bioinf.recipespaces.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.recipespaces.model.IngredientAmount;
import nl.bioinf.recipespaces.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.recipespaces.model.Step;
import nl.bioinf.recipespaces.recipespaces.service.IngredientAmountService;
import nl.bioinf.recipespaces.recipespaces.service.RecipeService;
import nl.bioinf.recipespaces.recipespaces.service.StepService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Controller()
public class RecipeController {
    private final RecipeService recipeService;
    private final StepService stepService;
    private final IngredientAmountService ingredientAmountService;

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
        Set<IngredientAmount> ingredientAmounts = ingredientAmountService.getIngredientAmountFromRecipe(id);
        try{
            model.addAttribute("recipes", recipe);
            model.addAttribute("ingredients", ingredients);
            model.addAttribute("steps", steps);
            model.addAttribute("ingredient_amount", ingredientAmounts);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "recipe";
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
