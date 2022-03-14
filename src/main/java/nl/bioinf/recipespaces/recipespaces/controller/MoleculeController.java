package nl.bioinf.recipespaces.recipespaces.controller;

import nl.bioinf.recipespaces.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.recipespaces.model.Molecule;
import nl.bioinf.recipespaces.recipespaces.service.MoleculeService;
import nl.bioinf.recipespaces.recipespaces.service.MoleculeService;
import nl.bioinf.recipespaces.recipespaces.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller()
@RequestMapping(path="molecule")
public class MoleculeController {
    private final MoleculeService moleculeService;

    @Autowired
    public MoleculeController(MoleculeService moleculeService) {
        this.moleculeService = moleculeService;
    }

    @GetMapping
    public List<Molecule> getAllMolecules() {
        return this.moleculeService.getAllIds();
    }

    // Get Molecule from ID
    @GetMapping("/{id}")
    public String displayMoleculeByID(Model model, @PathVariable("id") String id){
        Molecule molecule = moleculeService.getMolById(id);
        try{
            model.addAttribute("molecule", molecule);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "molecule";
    }


}