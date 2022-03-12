package nl.bioinf.recipespaces.recipespaces.controller;

import nl.bioinf.recipespaces.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.recipespaces.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller()
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // Get all recipes
    @GetMapping("/recipe")
    public String displayRecipes(Model model){
        List<Recipe> recipes = getXAmountOfRecipes(100000);
        model.addAttribute("recipes", recipes);
        return "recipe";
    }

    @GetMapping
    public List<Recipe> getXAmountOfRecipes(int amount) {
        return this.recipeService.getXAmountOfRecipes(amount);
    }


    // Get Recipe from ID
    @GetMapping("/recipe/{id}")
    public String displayRecipeByID(Model model, @PathVariable("id") String id){
        Recipe recipe = recipeService.getId(id);
        Set<Ingredient> ingredients = recipeService.getIngredientsFromRecipe(recipe.getId());
        try{
            model.addAttribute("recipes", recipe);
            model.addAttribute("ingredients", ingredients);
            System.out.println(ingredients);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "recipe";
    }

    @GetMapping("search/{recipeID}")
    @ResponseBody
    public Set<Ingredient> getIngredients(@PathVariable("recipeID") String id) throws ResponseStatusException {
        try{
            return this.recipeService.getIngredientsFromRecipe(id);
        } catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }


}
