package nl.bioinf.recipespaces.service;

import nl.bioinf.recipespaces.upload.CSVReader;
import nl.bioinf.recipespaces.model.IngredientData;
import nl.bioinf.recipespaces.dao.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {


    private UploadRepository uploadRepository;

    @Autowired
    public UploadService(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    //

    public void insertIngredient(String tagValue){ uploadRepository.insertIngredient(tagValue);}

    public void insertStep(String tagValue){ uploadRepository.insertStep(tagValue);}

    public void insertMolecule(Integer Id, String tagValue, String flavors){ uploadRepository.insertMolecule(Id, tagValue, flavors);}

    public void insertRecipe(String tagValue){ uploadRepository.insertRecipe(tagValue);}

    public void insertRecNerLink(Integer recId, Integer nerId){ uploadRepository.insertRecipeNer(recId, nerId);}
    public void insertRecStepLink(Integer recId, Integer steId){ uploadRepository.insertRecipeStep(recId, steId);}
    public void insertNerMol(Integer nerId, Integer  molId){ uploadRepository.insertNerMol(nerId, molId);}

    //

    public Integer getIdForRecipe(String name){ return uploadRepository.findRecipeId(name);}

    public Integer getIdForIngredient(String name){ return uploadRepository.findIngredientId(name);}

    public Integer getIdForMolecule(String name){ return uploadRepository.findMoleculeId(name);}

    public Integer getIdForStep(String name){ return uploadRepository.findStepId(name);}

    public IngredientData parseUploadedFile(final MultipartFile inFile){
        CSVReader csv = new CSVReader();
        return csv.ParseCSV(inFile);
    }
}
