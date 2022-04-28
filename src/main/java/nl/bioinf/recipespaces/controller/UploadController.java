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
        modelMap.put("recipeData", new RecipeData());
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String submit(@ModelAttribute("recipeData") RecipeData recipeData, @RequestParam("file") final MultipartFile file, final Model modelMap) {

        modelMap.addAttribute("file", file);
        IngredientData data = uploadService.parseUploadedFile(file);

        uploadService.insertRecipe(recipeData.getRecipeName());
        Integer recipeId = uploadService.getLatestId();

        uploadService.insertStep(recipeData.getSteps());
        Integer stepId = uploadService.getLatestId();

        // Insert links

        uploadService.insertRecStepLink(recipeId, stepId);

        for (Ingredient i : data.getIngredients()){

            uploadService.insertIngredient(i.getTagValue());
            Integer ingredientId = uploadService.getLatestId();
            uploadService.insertRecNerLink(recipeId, ingredientId);

        }

        for (Molecule m : data.getMolecules()){

            uploadService.insertMolecule(m.getPubChemId(), m.getCommonName(), m.getFlavorProfile());
            uploadService.insertNerMol(recipeId, m.getPubChemId());

        }

        return "fileUploadView";
    }


}
