package nl.bioinf.recipespaces.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nl.bioinf.recipespaces.helperClasses.Create2DMatrix;
import nl.bioinf.recipespaces.model.*;
import nl.bioinf.recipespaces.service.DistanceService;
import nl.bioinf.recipespaces.service.IngredientService;
import org.springframework.web.bind.annotation.*;
import smile.mds.MDS;

import java.lang.reflect.Type;
import java.util.*;

/**
 * Gets distances between ingredients and serves them to the /distance endpoint.
 * Also generates the MDS plot using other helper classes.
 * Returns a recipe space object.
 * @author Jurrien de Jong
 */

@RestController
public class DistanceController {

    private final DistanceService distanceService;
    private final IngredientService ingredientService;

    public DistanceController(DistanceService distanceService, IngredientService ingredientService) {
        this.distanceService = distanceService;
        this.ingredientService = ingredientService;
    }

    @PostMapping("/distance")
    public RecipeSpace distance(@RequestBody String id){
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        HashMap<String, String> res = gson.fromJson(id, type);
        Integer recipeId = Integer.valueOf(res.get("id"));
        List<Ingredient> ingredients;
        ingredients = distanceService.getIngredientsContainingDistance(recipeId);

        Create2DMatrix matrixObject = new Create2DMatrix(distanceService);
        MdsData data = matrixObject.generateMatrix(ingredients);

        MDS mds = MDS.of(data.getMatrix());

        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        for (double[] cord: mds.coordinates) {
            xValues.add(cord[0]);
            yValues.add(cord[1]);
        }

        return new RecipeSpace(xValues, yValues, data.getIngredientNames());
    }


}
