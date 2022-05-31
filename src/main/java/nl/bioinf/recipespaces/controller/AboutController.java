package nl.bioinf.recipespaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;

/**
 * Displays the about page
 * @author Rose Hazenberg
 */
@Controller
@RequestMapping("about")
public class AboutController {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AboutController.class.getName());

    @RequestMapping(method = RequestMethod.GET)
    public String about() {
        logger.log(Level.INFO, "Serving the about page");
        return "about";
    }
}
