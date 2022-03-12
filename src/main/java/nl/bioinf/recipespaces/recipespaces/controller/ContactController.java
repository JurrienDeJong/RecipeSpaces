package nl.bioinf.recipespaces.recipespaces.controller;

import nl.bioinf.recipespaces.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.recipespaces.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("test")
public class ContactController {

    @Autowired
    public ContactController() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getContact() {
        return "test";
    }
}
