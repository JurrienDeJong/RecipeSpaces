package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.service.IngredientAmountService;
import nl.bioinf.recipespaces.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileWriter;
import java.util.logging.Level;

/**
 * Displays the recipe spaces page
 * @author Jurriën de Jong
 */
@Controller
@RequestMapping("recipespace")
public class RecipeSpaceController {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RecipeSpaceController.class.getName());

    private static FileWriter file;
    private final IngredientAmountService ingredientAmountService;
    private final RecipeService recipeService;

    public RecipeSpaceController(IngredientAmountService ingredientAmountService, RecipeService recipeService) {
        this.ingredientAmountService = ingredientAmountService;
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public String spaces(Model model, @PathVariable("id") Integer id) {
        logger.log(Level.INFO, "Serving the page with the visualization of recipe spaces");
        model.addAttribute("recipeName", recipeService.getId(id).getTagValue());
        model.addAttribute("recipeId", id);
        return "recipe-space-view";
    }
}
