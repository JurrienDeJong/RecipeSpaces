package nl.bioinf.recipespaces.dao;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.Molecule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
/**
 * Statements used by MoleculeService
 * @author Jurriën de Jong
 */
@Repository
public interface MoleculeRepository extends JpaRepository<Molecule, Integer> {
    @Query(value = "SELECT DISTINCT s.pubchem_id, s.common_name, s.flavor_profile from mol s join ner_mol rs on s.pubchem_id = rs.pubchem_id where rs.ner_id = :ingID", nativeQuery = true)
    Set<Molecule> moleculesFromIngredient(@Param("ingID") Integer ingID);

    @Query(value = "SELECT DISTINCT m.pubchem_id, m.common_name, m.flavor_profile from mol m where m.pubchem_id = :pubID", nativeQuery = true)
    Molecule getMoleculeById(@Param("pubID") Integer pubID);

    @Query(value = "select common_name from mol m join ner_mol nm on m.pubchem_id = nm.pubchem_id where ner_id = :ingID", nativeQuery = true)
    Set<String> getCommonNames(Integer ingID);

    @Query(value = "SELECT DISTINCT n.tag_value from mol s join ner_mol rs on s.pubchem_id = rs.pubchem_id join ner n on rs.ner_id = n.ID where s.pubchem_id = :pubID", nativeQuery = true)
    Set<Ingredient> ingredientsFromMolecules(@Param("pubID") Integer pubID);
}
