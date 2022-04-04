package nl.bioinf.recipespaces.recipespaces.controller;

import nl.bioinf.recipespaces.recipespaces.model.*;
import nl.bioinf.recipespaces.recipespaces.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    private UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String displayForm(ModelMap modelMap) {
        modelMap.put("uploadData", new UploadData());
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String submit(@ModelAttribute("uploadData") UploadData uploadData, @RequestParam("file") final MultipartFile file, final Model modelMap) {
        modelMap.addAttribute("file", file);
        UploadData data = uploadService.parseUploadedFile(file);
        data.setRecipeTag(uploadData.getRecipeTag());
        data.setStep(uploadData.getStep());

        uploadService.insertRecipe(uploadData.getRecipeTag());
        uploadService.insertStep(uploadData.getStep());

        Integer stepId = uploadService.getIdForStep(uploadData.getStep());
        Integer recipeId = uploadService.getIdForRecipe(uploadData.getRecipeTag());

        // Insert link
        uploadService.insertRecStepLink(recipeId, stepId);

        for (Ingredient i : data.getIngredients()){
            uploadService.insertIngredient(i.getTagValue());
            // get Id
            Integer ingredientId = uploadService.getIdForIngredient(i.getTagValue());
            // Insert links
            uploadService.insertRecNerLink(recipeId, ingredientId);
        }
        return "fileUploadView";
    }


}
