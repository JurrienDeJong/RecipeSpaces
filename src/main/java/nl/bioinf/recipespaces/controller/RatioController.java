package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.service.IngredientAmountService;
import nl.bioinf.recipespaces.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Level;

/**
 * Handles view of ratio
 * @author Jorick Baron
 */
@Controller()
@RequestMapping(path = "ratio")
public class RatioController {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RatioController.class.getName());

    private final IngredientAmountService ingredientAmountService;
    private final RecipeService recipeService;

    public RatioController(IngredientAmountService ingredientAmountService, RecipeService recipeService) {
        this.ingredientAmountService = ingredientAmountService;
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public String showRatio(Model model, @PathVariable("id") Integer id) {
        logger.log(Level.INFO, "The ratio page of the recipe with id: " + id);
        recipeService.parseRecipe(model, id, recipeService, ingredientAmountService);



        logger.log(Level.INFO, "Returning ratio page with convertible ingredient amounts for recipe with id: " + id);
        return "ratios";
    }

}
