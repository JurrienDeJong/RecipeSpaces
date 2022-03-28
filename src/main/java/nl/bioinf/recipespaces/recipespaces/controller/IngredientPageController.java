package nl.bioinf.recipespaces.recipespaces.controller;

import nl.bioinf.recipespaces.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.recipespaces.service.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IngredientPageController {

    private final IngredientService ingredientService;

    public IngredientPageController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients")
    public String getHome(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients";
    }

    @PostMapping("/ingredients/ingredient")
    public String ingredientSubmit(@ModelAttribute Ingredient ingredient, Model model) {
        int missingId = ingredientService.missingId(ingredient.getTag_value());
        ingredient.setId(missingId);

        return "redirect:/ingredient/" + ingredient.getId();
    }

    @RequestMapping(value = "ingredients/ingredientSearchFromKeyword", method = RequestMethod.GET)
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
