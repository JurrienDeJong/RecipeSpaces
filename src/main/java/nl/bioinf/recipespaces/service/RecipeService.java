package nl.bioinf.recipespaces.service;

import com.google.gson.Gson;
import nl.bioinf.recipespaces.dao.IngredientAmountRepository;
import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.dao.IngredientRepository;
import nl.bioinf.recipespaces.model.IngredientAmount;
import nl.bioinf.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.dao.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

/**
 * Connects controller actions to the correct queries in RecipeRepository
 * @author Jurriën de Jong
 */
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientAmountRepository ingredientAmountRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, IngredientAmountRepository ingredientAmountRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientAmountRepository = ingredientAmountRepository;
    }

    public Set<Recipe> getAllRecipes() {
        return this.recipeRepository.selectAllRecipes();
    }

    public List<Recipe> getAllIds() {
        return this.recipeRepository.findAll();
    }

    public List<Integer> missingId(String keyword) { return this.recipeRepository.missingId(keyword);}

    public Recipe getId(Integer id) {
        return this.recipeRepository.findRecipeById(id);
    }

    public List<Recipe> findByKeyword(String keyword) { return this.recipeRepository.recipesFromKeyword(keyword);}

    public List<Recipe> findByExactKeyword(String keyword) { return this.recipeRepository.recipesFromExactKeyword(keyword);}

    public List<Ingredient> getIngredientsFromRecipe(Integer id) {
        return this.ingredientRepository.ingredientsFromRecipe(id);
    }


    public Set<Recipe> getRecipesFromIngredient(Integer id) {
        return this.recipeRepository.recipesFromIngredient(id);
    }

    public IngredientAmount getIngredientAmountFromRecipe(Integer recipeId) {
        return this.ingredientAmountRepository.ingredientAmountFromRecipe(recipeId);
    }

    public List<IngredientAmount> getIngredientAmountsFromRecipe(Integer recipeId) {
        return this.ingredientAmountRepository.ingredientAmountsFromRecipe(recipeId);
    }

    public HashMap<String, String> parseRecipe(Model model, Integer id, RecipeService recipeService, IngredientAmountService ingredientAmountService) {
        List<String> commonUnits = new ArrayList<>( Arrays.asList(
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
                    } else {
                        ingredientMap.put(ing.getTagValue(), words[0]);
                        break;
                    }
                }
            }
        }
        Gson gson = new Gson();
        model.addAttribute("ingredientAmounts", gson.toJson(ingredientMap));
        model.addAttribute("recipeName", recipe.getTagValue());
        return ingredientMap;
    }
}
