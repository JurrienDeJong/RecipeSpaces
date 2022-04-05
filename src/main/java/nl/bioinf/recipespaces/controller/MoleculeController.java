package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.model.Molecule;
import nl.bioinf.recipespaces.service.MoleculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String displayMoleculeByID(Model model, @PathVariable("id") Integer id){
        Molecule molecule = moleculeService.getMolById(id);
        try{
            model.addAttribute("molecule", molecule);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "molecule";
    }


}
