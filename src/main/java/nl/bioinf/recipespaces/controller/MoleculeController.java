package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.Molecule;
import nl.bioinf.recipespaces.service.MoleculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * Handles molecule view
 * @author Jurriën de Jong
 */
@Controller()
@RequestMapping(path="molecule")
public class MoleculeController {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MoleculeController.class.getName());

    private final MoleculeService moleculeService;

    @Autowired
    public MoleculeController(MoleculeService moleculeService) {
        this.moleculeService = moleculeService;
    }

    @GetMapping
    public List<Molecule> getAllMolecules() {
        return this.moleculeService.getAllIds();
    }

    /**
     * Get molecule by id
     * @param model, to add attributes to use in html
     * @param id, integer with the id of a molecule
     * @return html with a molecule
     */
    @GetMapping("/{id}")
    public String displayMoleculeByID(Model model, @PathVariable("id") Integer id){
        logger.log(Level.INFO, "Retrieving molecule with id: " + id + " without warnings");

        Molecule molecule = moleculeService.getMolById(id);
        Set<Ingredient> ingredient = moleculeService.getIngredientsFromMolecules(id);
        try{
            model.addAttribute("molecule", molecule);
            model.addAttribute("ingredients", ingredient);
            logger.log(Level.INFO, "Adding molecule data to the model");
        } catch (Exception e){
            logger.log(Level.SEVERE, "Something went wrong; cause= " + e.getCause() + ", message= " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return "molecule";
    }


}
