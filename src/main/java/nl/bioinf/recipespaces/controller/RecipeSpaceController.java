package nl.bioinf.recipespaces.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.bioinf.recipespaces.model.*;
import nl.bioinf.recipespaces.service.IngredientAmountService;
import nl.bioinf.recipespaces.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

/**
 * Displays the about page
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
    public String spaces(Model model, @PathVariable("id") Integer id) throws IOException {
        logger.log(Level.INFO, "Serving the page with the visualization of recipe spaces");
        HashMap<String, String> recipeMap = recipeService.parseRecipe(model, id, recipeService, ingredientAmountService);

        createVisualisation(recipeMap);
        return "recipeSpaceView";
    }

    private void createVisualisation(HashMap<String, String> recipeMap) throws IOException {
        logger.log(Level.INFO, "Creating visualization");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Node> nodes = new ArrayList<>();
        List<Link> links = new ArrayList<>();

        for (String ingredient : recipeMap.keySet()) {
            nodes.add(new Node(ingredient, 1));
        }

        for (Node node : nodes) {
            links.add(new Link(node.id(), nodes.get(1).id(), 100));
        }

//        RecipeSpace recipeSpace = new RecipeSpace(nodes, links);
//
//        writeToFile(gson, recipeSpace);
    }

    private void writeToFile(Gson gson, RecipeSpace recipeSpace) {
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/static/json/recipespace_original.json");
            myWriter.write(gson.toJson(recipeSpace));
            myWriter.close();
            logger.log(Level.INFO, "Creating json when without error's");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Something went wrong; cause= " + e.getCause() + ", message= " + e.getMessage());
            e.printStackTrace();
        }
    }
}
