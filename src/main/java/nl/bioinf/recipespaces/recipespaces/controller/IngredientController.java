package nl.bioinf.recipespaces.recipespaces.controller;

import nl.bioinf.recipespaces.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.recipespaces.service.IngredientService;
import nl.bioinf.recipespaces.recipespaces.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller()
@RequestMapping(path="/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService recipeService) {
        this.ingredientService = recipeService;
    }

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return this.ingredientService.getAllIds();
    }

    // Get Recipe from ID
    @GetMapping("/{id}")
    public String displayRecipeByID(Model model, @PathVariable("id") String id){
        Ingredient ingredient = ingredientService.getId(id);
        try{
            model.addAttribute("ingredient", ingredient);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "ingredient";
    }
//
//    @GetMapping("{id}")
//    @ResponseBody
//    public Ingredient getIngredientByID(Model model, @PathVariable("id") String id) throws ResponseStatusException {
//        Optional<Ingredient> rv = this.ingredientService.getId(id);
//        return rv.orElseThrow ( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }
}
