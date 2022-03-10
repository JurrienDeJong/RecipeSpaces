package nl.bioinf.recipespaces.recipespaces.controller;

import nl.bioinf.recipespaces.recipespaces.model.Step;
import nl.bioinf.recipespaces.recipespaces.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController()
@RequestMapping(path="step")
public class StepController {
    private final StepService stepService;

    @Autowired
    public StepController(StepService stepService) {
        this.stepService = stepService;
    }

    @GetMapping
    public List<Step> getAllIngredients() {
        return this.stepService.getAllIds();
    }

    @GetMapping("{id}")
    @ResponseBody
    public Step getStepByID(Model model, @PathVariable("id") String id) throws ResponseStatusException {
        Optional<Step> rv = this.stepService.getId(id);
        return rv.orElseThrow ( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("search/{recipeID}")
    @ResponseBody
    public Set<Step> getStepsFromRecipe(@PathVariable("recipeID") String recipeID) throws ResponseStatusException {
        try{
            return this.stepService.getStepsFromRecipe(recipeID);
        } catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
