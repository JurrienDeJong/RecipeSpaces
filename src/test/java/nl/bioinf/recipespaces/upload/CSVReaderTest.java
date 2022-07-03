package nl.bioinf.recipespaces.upload;

import nl.bioinf.recipespaces.helperClasses.CSVReader;
import nl.bioinf.recipespaces.model.IngredientData;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

class CSVReaderTest {
    @Test
    void checkCSVInput(){
        try{
            CSVReader reader = new CSVReader();

            MultipartFile multipartFile = new MockMultipartFile("test.csv", new FileInputStream("src/main/resources/static/exampleData.csv"));
            IngredientData data = reader.ParseCSV(multipartFile);
            System.out.println(data.getMolecules().toString());
        } catch(Exception ex){
            ex.getCause();
        }

    }

}