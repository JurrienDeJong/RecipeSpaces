package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.service.IngredientService;
import nl.bioinf.recipespaces.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

/**
 * Responsible for serving the user the home page
 * @author Jorick Baron
 */
@Controller
public class HomeController {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HomeController.class.getName());

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @Autowired
    public HomeController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    /**
     * To get distances
     * @return distance page
     */
    @GetMapping("/distance")
    public String getDistance(){
        return "distance";
    }

    /**
     * Add the recipe and ingredient to the model for the home page
     * @param model to add data for html
     * @return home page
     */
    @GetMapping({"/home", "/", "/index"})
    public String getHome(Model model) {
        logger.log(Level.INFO, "Serving the home page with a Recipe and Ingredient model");
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("ingredient", new Ingredient());
        return "home";
    }

    /**
     * Searches for a recipe or an ingredient based on the option
     * @param option recipe of ingredient
     * @param recipe recipe model
     * @param ingredient ingredient model
     * @return page with multiple recipe if there are more of one recipe. Recipe page when a recipe is searched.
     * Ingredient page when an ingredient is searched. Or 500 error page when recipe or ingredient is not found.
     */
    @RequestMapping(value="/home/search", method = RequestMethod.POST)
    public String submit(@RequestParam("options") String option, Recipe recipe, Ingredient ingredient) {
        if (Objects.equals(option, "recipe")) {
            logger.log(Level.INFO, "Fetching " + option + " : " + recipe.getTagValue());
            List<Integer> missingIds = recipeService.missingId(recipe.getTagValue());
            if (missingIds.size() > 1) {
                logger.log(Level.INFO, "Serving the multiple recipes of " + recipe.getTagValue());
                return "redirect:/recipe/multiple/" + recipe.getTagValue();
            } else {
                logger.log(Level.INFO, "Serving recipe " + recipe.getTagValue() + " without having multiple recipes");
                recipe.setId(missingIds.get(0));
            }

            return "redirect:/recipe/" + recipe.getId();
        } else if (Objects.equals(option, "ingredient")){
            logger.log(Level.INFO, "Fetching " + option + " : " + ingredient.getTagValue());
            Integer missingId = ingredientService.missingId(ingredient.getTagValue());
            ingredient.setId(missingId);
            return "redirect:/ingredient/" + ingredient.getId();
        }
        else {
            logger.log(Level.SEVERE, "The " + option + " has an 500 error, it doesn't exists or there is no data available");
            return "redirect:/error-500";
        }
    }


    /**
     * Get recipes autocompleted.
     * @return all recipenames containing keyword
     */
    @RequestMapping(value = "home/recipeSearchFromKeyword", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getRecipesAutocompleted(@RequestParam(value = "term", defaultValue = "") String term){
        logger.log(Level.INFO, "Returning all of the recipes of " + term);
        List<String> allRecipeNames = new ArrayList<>();
        List<Recipe> recipes = recipeService.findByKeyword(term);
        for (Recipe r: recipes){
            allRecipeNames.add(r.getTagValue());
        }
        return allRecipeNames;
    }

    /**
     * Get ingredients autocompleted.
     * @return all ingredientnames containing keyword
     */
    @RequestMapping(value = "home/ingredientSearchFromKeyword", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getIngredientAutocompleted(@RequestParam(value = "term", defaultValue = "") String term){
        logger.log(Level.INFO, "Returning all of the ingredient: " + term);
        List<String> allIngredientNames = new ArrayList<>();
        List<Ingredient> ingredients = ingredientService.findByKeyword(term);
        for (Ingredient i: ingredients){
            allIngredientNames.add(i.getTagValue());
        }
        return allIngredientNames;
    }
}
