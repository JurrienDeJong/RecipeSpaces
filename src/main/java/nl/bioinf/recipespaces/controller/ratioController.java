package nl.bioinf.recipespaces.controller;

import com.google.gson.Gson;
import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.IngredientAmount;
import nl.bioinf.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.service.IngredientAmountService;
import nl.bioinf.recipespaces.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Handles view of ratio
 * Author: Jorick Baron
 */



@Controller()
@RequestMapping(path = "ratio")
public class ratioController {

    private final IngredientAmountService ingredientAmountService;
    private final RecipeService recipeService;

    private final List<String> commonUnits = new ArrayList<>( Arrays.asList(
            //volume
            "teaspoon", "teaspoons", "t", "t.", "tsp.",
            "tablespoon", "tablespoons", "tbl.", "tbs.", "tbsp.",
            "fluid ounce", "fl oz", "fl oz.",
            "gill",
            "cup", "cups", "c.", "c",
            "pint", "pints", "p", "p.", "pt", "pt.", "fl pt", "fl pt.",
            "quart", "quarts", "q", "q.", "qt", "qt.", "fl qt", "fl qt.",
            "gallon", "gallons", "g", "g.", "gal", "gal.",
            "milliliter", "milliliters", "millilitre", "millilitres", "ml", "ml", "cc", "cc.",
            "liter", "liters", "litre", "litres", "l", "l.",
            "deciliter", "deciliters", "decilitre", "decilitres", "dl", "dl.",
            //weight
            "pound", "pounds", "lb", "lb.",
            "ounce", "ounces", "oz", "oz.",
            "milligram", "milligrams", "milligramme", "milligrammes", "mg", "mg.",
            "gram", "grams", "gramme", "grammes", "gr", "gr.", "g", "g.",
            "kilogram", "kilograms", "kilogramme", "kilogrammes", "kilo", "kilos", "kg", "kg.",
            //parts
            "whole", "half", "quarter", "part", "parts", "leaf", "leaves"
    ));

    public ratioController(IngredientAmountService ingredientAmountService, RecipeService recipeService) {
        this.ingredientAmountService = ingredientAmountService;
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public String showRatio(Model model, @PathVariable("id") Integer id) {
        Recipe recipe = recipeService.getId(id);
        List<IngredientAmount> ingredientAmounts = ingredientAmountService.getIngredientAmountsFromRecipe(id);
        List<Ingredient> ingredients = recipeService.getIngredientsFromRecipe(id);
        HashMap<String, String> ingredientMap = new HashMap<>();
        for (IngredientAmount ingA : ingredientAmounts) {
            for (Ingredient ing : ingredients) {
                String[] words = ingA.getTagValue().split(" ");
                if (ingA.getTagValue().contains(ing.getTagValue()) && Character.isDigit(words[0].charAt(0))) {
                    if (Character.isDigit(words[0].charAt(words[0].length() -1))) {
                        for (int i = 1; i < words.length -1; i++) {
                            if (commonUnits.contains(words[i].toLowerCase())) {
                                ingredientMap.put(ing.getTagValue(), words[0] + words[i]);
                                break;
                            } else {
                                ingredientMap.put(ing.getTagValue(), words[0] + ing.getTagValue());
                            }
                        }
                    }else {
                        ingredientMap.put(ing.getTagValue(), words[0]);
                        break;
                    }
                }
            }
        }
        Gson gson = new Gson();
        model.addAttribute("ingredientAmounts", gson.toJson(ingredientMap));
        model.addAttribute("recipeName", recipe.getTagValue());

        return "ratios";
    }

}
