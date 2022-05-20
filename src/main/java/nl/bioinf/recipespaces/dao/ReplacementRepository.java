package nl.bioinf.recipespaces.dao;

import nl.bioinf.recipespaces.model.ReplacementData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ReplacementRepository extends JpaRepository<ReplacementData, String> {
    @Query(value = "select ner_id, common_name from mol m join ner_mol nm on m.pubchem_id = nm.pubchem_id", nativeQuery = true)
    List<Map<String, String>> getReplacementData();

    @Query(value = "select distinct ner_id from mol join ner_mol nm on mol.pubchem_id = nm.pubchem_id", nativeQuery = true)
    List<Integer> getIdsContainingMolecules();
}
