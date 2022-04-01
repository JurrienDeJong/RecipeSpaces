package nl.bioinf.recipespaces.recipespaces.service;

import nl.bioinf.recipespaces.recipespaces.model.Molecule;
import nl.bioinf.recipespaces.recipespaces.model.MoleculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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


}
