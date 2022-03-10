package nl.bioinf.recipespaces.recipespaces.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MoleculeRepository extends JpaRepository<Molecule, String> {
    @Query(value = "SELECT DISTINCT m.pubchem_id, m.common_name, m.flavor_profile from mol m join ner_mol nm on m.pubchem_id = nm.pubchem_id where nm.ner_id = :ingID", nativeQuery = true)
    Set<Molecule> moleculesFromIngredient(@Param("ingID") String ingID);
}
