package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.MDS.Create2DMatrix;
import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.IngredientDistance;
import nl.bioinf.recipespaces.model.RecipeSpace;
import nl.bioinf.recipespaces.service.DistanceService;
import nl.bioinf.recipespaces.service.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.util.ArrayUtils;
import smile.mds.MDS;

import java.util.*;

@Controller
public class DistanceController {

    private final DistanceService distanceService;
    private final IngredientService ingredientService;

    public DistanceController(DistanceService distanceService, IngredientService ingredientService) {
        this.distanceService = distanceService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/distance")
    public String distance(){
        List<Ingredient> ingredients = ingredientService.getIngredientsFromRecipe(123767);
        Create2DMatrix matrixObject = new Create2DMatrix(distanceService);
        double[][] matrix = matrixObject.generateMatrix(ingredients);

        System.out.println(Arrays.deepToString(matrix));
        System.out.println(matrix.length);

        MDS mds = MDS.of(matrix);

        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        for (double[] cord: mds.coordinates) {
            xValues.add(cord[0]);
            yValues.add(cord[1]);
        }

        RecipeSpace recipeSpace = new RecipeSpace(xValues, yValues);

        System.out.println(recipeSpace);

        return "distance";
    }
}
