package nl.bioinf.recipespaces.recipespaces.Upload;

import nl.bioinf.recipespaces.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.recipespaces.model.UploadData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {

    List<Ingredient> ingredients = new ArrayList<>();

    public UploadData ParseCSV(){
        String line = "";
        String splitBy = ";";
        try
        {
            // CSVDemo.csv does not exist.. it is just a placeholder

            BufferedReader br = new BufferedReader(new FileReader("CSVDemo.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] data = line.split(splitBy);
                Recipe recipe = new Recipe(data[0]);
                for (String i : data[1].split(",")){
                    ingredients.add(new Ingredient(i));
                }
                return new UploadData(recipe, ingredients);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
