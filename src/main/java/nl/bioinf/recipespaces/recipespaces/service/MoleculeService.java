package nl.bioinf.recipespaces.recipespaces.service;

import nl.bioinf.recipespaces.recipespaces.model.Molecule;
import nl.bioinf.recipespaces.recipespaces.model.MoleculeRepository;
import nl.bioinf.recipespaces.recipespaces.model.Step;
import nl.bioinf.recipespaces.recipespaces.model.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<Molecule> getId(String id) {
        return this.moleculeRepository.findById(id);
    }

    public Set<Molecule> getMoleculesFromIngredient(String ingID) { return this.moleculeRepository.moleculesFromIngredient(ingID);}
}
