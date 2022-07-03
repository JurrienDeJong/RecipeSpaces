package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.logging.HtmlLogFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

/**
 * Displays the about page
 * @author Rose Hazenberg
 */
@Controller
@RequestMapping("about")
public class AboutController {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AboutController.class.getName());

    /**
     * This will serve the about page
     * @return about page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String about() {
        logger.log(Level.INFO, "Serving the about page");
        return "about";
    }
}
