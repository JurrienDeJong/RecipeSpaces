package nl.bioinf.recipespaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Displays the about page
 * @author Rose Hazenberg
 */
@Controller
@RequestMapping("about")
public class AboutController {

    @RequestMapping(method = RequestMethod.GET)
    public String about() {
        return "about";
    }
}
