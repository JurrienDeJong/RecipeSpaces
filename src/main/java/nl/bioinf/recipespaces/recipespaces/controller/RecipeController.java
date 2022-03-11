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

    @GetMapping("/recipe")
    public String displayRecipes(Model model){
        List<Recipe> recipes = getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "recipe";
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return this.recipeService.getAllIds();
    }

    @GetMapping("/recipe/{id}")
    @ResponseBody
    public Recipe getRecipeByID(@PathVariable("id") String id) throws ResponseStatusException {
        Optional<Recipe> rv = this.recipeService.getId(id);
        System.out.println(rv);
        return rv.orElseThrow ( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
