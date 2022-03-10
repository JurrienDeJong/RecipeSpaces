package nl.bioinf.recipespaces.recipespaces.controller;

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

@RestController()
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

    @GetMapping("{id}")
    @ResponseBody
    public Molecule getMoleculeByID(@PathVariable("id") String id) throws ResponseStatusException {
        Optional<Molecule> rv = this.moleculeService.getId(id);
        return rv.orElseThrow ( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("search/{ingID}")
    @ResponseBody
    public Set<Molecule> getMoleculesFromIngredient(@PathVariable("ingID") String ingID) throws ResponseStatusException {
        try{
            return this.moleculeService.getMoleculesFromIngredient(ingID);
        } catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
