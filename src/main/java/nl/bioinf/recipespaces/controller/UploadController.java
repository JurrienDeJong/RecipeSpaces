package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.IngredientData;
import nl.bioinf.recipespaces.model.*;
import nl.bioinf.recipespaces.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles file uploads by user
 * @author Jurriën de Jong
 */
@Controller
public class UploadController {

    @Autowired
    private UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String displayForm(ModelMap modelMap) {
        modelMap.put("ingredientData", new IngredientData());
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String submit(@ModelAttribute("ingredientData") IngredientData ingredientData, @RequestParam("file") final MultipartFile file, final Model modelMap) {
//        modelMap.addAttribute("file", file);
//        IngredientData data = uploadService.parseUploadedFile(file);
//        data.setRecipeTag(ingredientData.getRecipeTag());
//        data.setStep(ingredientData.getStep());
//
//        uploadService.insertRecipe(ingredientData.getRecipeTag());
//        uploadService.insertStep(ingredientData.getStep());
//
//        Integer stepId = uploadService.getIdForStep(ingredientData.getStep());
//        Integer recipeId = uploadService.getIdForRecipe(ingredientData.getRecipeTag());
//
//        // Insert link
//        uploadService.insertRecStepLink(recipeId, stepId);
//
//        for (Ingredient i : data.getIngredients()){
//            uploadService.insertIngredient(i.getTagValue());
//            // get Id
//            Integer ingredientId = uploadService.getIdForIngredient(i.getTagValue());
//            // Insert links
//            uploadService.insertRecNerLink(recipeId, ingredientId);
//        }
        return "fileUploadView";
    }


}
