package nl.bioinf.recipespaces.service;

import nl.bioinf.recipespaces.model.Step;
import nl.bioinf.recipespaces.dao.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StepService {

    private final StepRepository stepRepository;

    @Autowired
    public StepService(StepRepository recipeRepository) {
        this.stepRepository = recipeRepository;
    }

    public List<Step> getAllIds() {
        return this.stepRepository.findAll();
    }

    public Optional<Step> getId(Integer id) {
        return this.stepRepository.findById(id);
    }

    public Set<Step> getStepsFromRecipe(Integer recipeID) { return this.stepRepository.stepsFromRecipe(recipeID);}
}
