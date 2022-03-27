package nl.bioinf.recipespaces.recipespaces.controller;

import nl.bioinf.recipespaces.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.recipespaces.service.IngredientService;
import nl.bioinf.recipespaces.recipespaces.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public HomeController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("ingredient", new Ingredient());
        return "home";
    }

    @RequestMapping(value="/home/search", method = RequestMethod.POST)
    public String submit(@RequestParam("options") String option, Recipe recipe, Ingredient ingredient) {
        if (Objects.equals(option, "recipe")){
            String missingId = recipeService.missingId(recipe.getTag_value());
            recipe.setId(missingId);
            return "redirect:/recipe/" + recipe.getId();
        }else if (Objects.equals(option, "ingredient")){
            String missingId = ingredientService.missingId(ingredient.getTag_value());
            ingredient.setId(missingId);
            return "redirect:/ingredient/" + ingredient.getId();
        }
        else {
            return "redirect:/error-500";
        }
    }



    @RequestMapping(value = "home/recipeSearchFromKeyword", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getRecipesAutocompleted(@RequestParam(value = "term", defaultValue = "") String term){
        List<String> allRecipeNames = new ArrayList<>();
        List<Recipe> recipes = recipeService.findByKeyword(term);
        for (Recipe r: recipes){
            allRecipeNames.add(r.getTag_value());
        }
        return allRecipeNames;
    }
    @RequestMapping(value = "home/ingredientSearchFromKeyword", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getIngredientAutocompleted(@RequestParam(value = "term", defaultValue = "") String term){
        List<String> allIngredientNames = new ArrayList<>();
        List<Ingredient> ingredients = ingredientService.findByKeyword(term);
        for (Ingredient i: ingredients){
            allIngredientNames.add(i.getTag_value());
        }
        return allIngredientNames;
    }
}
