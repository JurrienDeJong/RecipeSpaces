package nl.bioinf.recipespaces.upload;

import nl.bioinf.recipespaces.model.IngredientData;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {
    @Test
    void checkCSVInput(){
        try{
            CSVReader reader = new CSVReader();

            MultipartFile multipartFile = new MockMultipartFile("test.csv", new FileInputStream("C:\\Users\\jurri\\Bureaublad\\School Files\\RecipeSpaces\\src\\main\\resources\\static\\exampleData.csv"));
            IngredientData data = reader.ParseCSV(multipartFile);
            System.out.println(data.getMolecules().toString());
        } catch(Exception ex){
            ex.getCause();
        }

    }

}