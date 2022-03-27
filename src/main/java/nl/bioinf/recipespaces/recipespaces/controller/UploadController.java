package nl.bioinf.recipespaces.recipespaces.controller;

import nl.bioinf.recipespaces.recipespaces.Upload.ReadCSV;
import nl.bioinf.recipespaces.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.recipespaces.model.UploadData;
import nl.bioinf.recipespaces.recipespaces.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadController {

    private UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @GetMapping("/upload")
    String getUpload(){
        ReadCSV csv = new ReadCSV();
        UploadData data = csv.ParseCSV();

        uploadService.insertRecipe(data.recipe().getTag_value());
        for (Ingredient i : data.ingredients()){
            uploadService.insertIngredient(i.getTag_value());

            // Get Id's
            Integer recipeId = uploadService.getIdForRecipe(data.recipe().getTag_value());
            Integer ingredientId = uploadService.getIdForIngredient(i.getTag_value());

            // Insert links
            uploadService.insertRecNerLink(recipeId, ingredientId);
        }

        return "upload";
    }
}
