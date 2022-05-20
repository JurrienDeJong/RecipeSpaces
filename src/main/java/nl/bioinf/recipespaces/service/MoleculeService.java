package nl.bioinf.recipespaces.service;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.Molecule;
import nl.bioinf.recipespaces.dao.MoleculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Int;

import java.util.List;
import java.util.Set;
/**
 * Connects controller actions to the correct queries in MoleculeRepository
 * @author Jurriën de Jong
 */
@Service
public class MoleculeService {

    private final MoleculeRepository moleculeRepository;

    @Autowired
    public MoleculeService(MoleculeRepository moleculeRepository) {
        this.moleculeRepository = moleculeRepository;
    }

    public List<Molecule> getAllIds() {
        return this.moleculeRepository.findAll();
    }

    public Molecule getMolById(Integer id) {
        return this.moleculeRepository.getMoleculeById(id);
    }

    public Set<Molecule> getMoleculesFromIngredient(Integer ingID) { return this.moleculeRepository.moleculesFromIngredient(ingID);}

    public Set<Ingredient> getIngredientsFromMolecules(Integer pubID) {
        return this.moleculeRepository.ingredientsFromMolecules(pubID);
    }

    public Set<String> getCommonNames(Integer Id){ return this.moleculeRepository.getCommonNames(Id);}
}
