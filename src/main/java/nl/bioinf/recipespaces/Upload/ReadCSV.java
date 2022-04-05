package nl.bioinf.recipespaces.Upload;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.Molecule;
import nl.bioinf.recipespaces.model.Step;
import nl.bioinf.recipespaces.model.UploadData;
import nl.bioinf.recipespaces.recipespaces.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {

    List<Ingredient> ingredients = new ArrayList<>();
    List<Step> steps = new ArrayList<>();
    List<Molecule> molecules = new ArrayList<>();

    public UploadData ParseCSV(MultipartFile file){

        String line = "";
        try{

            BufferedReader fileReader = new BufferedReader(new
                    InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            while ((line = fileReader.readLine()) != null)   //returns a Boolean value
            {
                StringBuilder flavors = new StringBuilder();
                String[] data = line.split(";");
                String IngredientTag = data[0];
                ingredients.add(new Ingredient(IngredientTag));
                for (int i = 1; i < data.length; i++) {
                    String[] moleculeData = data[i].split(",");
                    String molCommonName = moleculeData[0];
                    Integer molId = Integer.parseInt(moleculeData[1]);
                    for (int j = 0; j < moleculeData.length - 2; j++) {
                        flavors.append(moleculeData[j + 2]).append(",");
                    }
                    Molecule molecule = new Molecule(molId, molCommonName, flavors.toString());
                    molecules.add(molecule);
                }
            }
            return new UploadData(ingredients, molecules);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
