package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.Molecule;
import nl.bioinf.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.service.IngredientService;
import nl.bioinf.recipespaces.service.MoleculeService;
import nl.bioinf.recipespaces.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * Handles ingredient view
 * @author Jurriën de Jong
 */
@Controller()
@RequestMapping(path="/ingredient")
public class IngredientController {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(IngredientController.class.getName());

    private final IngredientService ingredientService;
    private final MoleculeService moleculeService;
    private final RecipeService recipeService;

    @Autowired
    public IngredientController(IngredientService ingredientService, MoleculeService moleculeService, RecipeService recipeService) {
        this.ingredientService = ingredientService;
        this.moleculeService = moleculeService;
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return this.ingredientService.getAllIds();
    }

    /**
     * Get ingredient by id
     * @param model, to add attributes to use in html
     * @param id, integer with the id of an ingredient
     * @return a html with the ingredient
     */
    @GetMapping("/{id}")
    public String displayRecipeByID(Model model, @PathVariable("id") Integer id){
        logger.log(Level.INFO, "Retrieving ingredient with id: " + id + " without warnings");
        logger.log(Level.WARNING, "Retrieving can be slow due to the large database");

        Ingredient ingredient = ingredientService.getId(id);
        Set<Molecule> molecules = moleculeService.getMoleculesFromIngredient(id);
        Set<Recipe> recipes = recipeService.getRecipesFromIngredient(id);
        try{
            model.addAttribute("ingredient", ingredient);
            model.addAttribute("molecules", molecules);
            model.addAttribute("recipes", recipes);
            logger.log(Level.INFO, "Adding ingredient data to the model");
        } catch (Exception e){
            logger.log(Level.SEVERE, "Something went wrong; cause= " + e.getCause() + ", message= " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return "ingredient";
    }
}
