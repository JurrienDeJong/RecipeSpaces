package nl.bioinf.recipespaces.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.bioinf.recipespaces.model.Link;
import nl.bioinf.recipespaces.model.Node;
import nl.bioinf.recipespaces.model.RecipeSpace;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Displays the about page
 * @author Rose Hazenberg
 */
@Controller
@RequestMapping("recipespace")
public class RecipeSpaceController {
    private static FileWriter file;

    @RequestMapping(method = RequestMethod.GET)
    public String spaces() throws IOException {
        createVisualisation();
        return "recipeSpaceView";
    }

    private void createVisualisation() throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Node n1 = new Node("node1", 1);
        Node n2 = new Node("node2", 2);
        Node n3 = new Node("node3", 3);

        Link l1 = new Link(n1.id(), n2.id(), 50);
        Link l2 = new Link(n1.id(), n3.id(), 200);
        Link l3 = new Link(n2.id(), n3.id(), 500);

        List<Node> nodes = new ArrayList<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);

        List<Link> links = new ArrayList<>();
        links.add(l1);
        links.add(l2);
        links.add(l3);

        RecipeSpace recipeSpace = new RecipeSpace(nodes, links);

        writeToFile(gson, recipeSpace);
    }

    private void writeToFile(Gson gson, RecipeSpace recipeSpace) {
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/static/json/recipespace.json");
            myWriter.write(gson.toJson(recipeSpace));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
