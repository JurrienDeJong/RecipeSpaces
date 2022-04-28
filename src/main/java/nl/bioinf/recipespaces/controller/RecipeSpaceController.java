package nl.bioinf.recipespaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Displays the about page
 * @author Rose Hazenberg
 */
@Controller
@RequestMapping("recipespace")
public class RecipeSpaceController {

    @RequestMapping(method = RequestMethod.GET)
    public String spaces() {


        return "recipeSpaceView";
    }
}
