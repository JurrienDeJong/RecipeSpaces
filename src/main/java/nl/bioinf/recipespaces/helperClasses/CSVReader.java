package nl.bioinf.recipespaces.helperClasses;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.IngredientData;
import nl.bioinf.recipespaces.model.Molecule;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads a multipart csv file given by a user.
 * @Author Jurrien de Jong
 */

public class CSVReader {

    private List<Ingredient> ingredients = new ArrayList<>();
    private List<Molecule> molecules = new ArrayList<>();

    public IngredientData ParseCSV(MultipartFile file){

        String line = "";
        try{

            BufferedReader fileReader = new BufferedReader(new
                    InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            while ((line = fileReader.readLine()) != null)   //returns a Boolean value
            {
                readIngredientLine(line);
            }
            return new IngredientData(ingredients, molecules);

        } catch (Exception ex) {
            throw new RuntimeException("Could not parse file!" + ex.getCause() + "\n" + ex.getMessage());
        }
    }

    private void readIngredientLine(String line) {
        StringBuilder flavors = new StringBuilder();
        String[] data = line.split(";");
        String IngredientTag = data[0];
        ingredients.add(new Ingredient(IngredientTag));
        for (int i = 1; i < data.length; i++) {
            readMoleculeLine(flavors, data[i]);
            flavors.delete(0, flavors.length());
        }
    }

    private void readMoleculeLine(StringBuilder flavors, String data) {
        String[] moleculeData = data.split(",");
        String molCommonName = moleculeData[0];
        Integer molId = Integer.parseInt(moleculeData[1]);
        for (int j = 2; j < moleculeData.length; j++) {
            flavors.append(moleculeData[j]);
            if(j < moleculeData.length - 1){
                flavors.append(",");
            }
        }
        Molecule molecule = new Molecule(molId, molCommonName, flavors.toString());
        molecules.add(molecule);
    }

}
