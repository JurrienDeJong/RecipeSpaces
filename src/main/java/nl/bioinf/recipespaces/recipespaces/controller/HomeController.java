package nl.bioinf.recipespaces.recipespaces.controller;

import nl.bioinf.recipespaces.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.recipespaces.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final RecipeService recipeService;

    public HomeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "home";
    }

    @PostMapping("/home")
    public String recipeSubmit(@ModelAttribute Recipe recipe, Model model) {
        model.addAttribute("recipes", recipe);
        return "recipe";
    }

    @RequestMapping(value = "home/recipeSearchFromKeyword", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getRecipesAutocompleted(Model model, @RequestParam(value = "term", defaultValue = "") String term){
        List<String> allRecipeNames = new ArrayList<>();
        List<Recipe> recipes = recipeService.findByKeyword(term);
        for (Recipe r: recipes){
            allRecipeNames.add(r.getTag_value());
        }
        return allRecipeNames;
    }
}
