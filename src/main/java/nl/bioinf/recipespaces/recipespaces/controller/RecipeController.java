package nl.bioinf.recipespaces.recipespaces.controller;

import nl.bioinf.recipespaces.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.recipespaces.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(path="api/v1/recipe")
public class RecipeController {
    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return this.recipeService.getAllIds();
    }

    @GetMapping("{id}")
    @ResponseBody
    public Recipe getRecipeByID(@PathVariable("id") String id) throws ResponseStatusException {
        Optional<Recipe> rv = this.recipeService.getId(id);
        System.out.println(rv);
        return rv.orElseThrow ( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}