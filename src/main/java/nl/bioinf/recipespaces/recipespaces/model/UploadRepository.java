package nl.bioinf.recipespaces.recipespaces.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface UploadRepository extends JpaRepository<Recipe, String> {
    @Transactional
    @Modifying
    @Query(value = "insert into recipe (tag, tag_value) values ('title', :tagValue)", nativeQuery = true)
    void insertRecipe(@Param("tagValue") String tagValue);

    @Transactional
    @Modifying
    @Query(value = "insert into ner (tag, tag_value) values ('ner', :tagValue)", nativeQuery = true)
    void insertIngredient(@Param("tagValue") String tagValue);


    @Transactional
    @Modifying
    @Query(value = "insert into step (tag, tag_value) values ('step', :tagValue)", nativeQuery = true)
    void insertStep(@Param("tagValue") String tagValue);

    @Transactional
    @Modifying
    @Query(value = "insert into mol (pubchem_id, common_name, flavor_profile) values (:Id, :tagValue, :flavors)", nativeQuery = true)
    void insertMolecule(@Param("Id") Integer Id, @Param("tagValue") String tagValue, @Param("flavors") String flavors);

    // Link Tables;

    @Transactional
    @Modifying
    @Query(value = "insert into recipe_ner (recipe_id, ner_id) values (:recId, :nerId)", nativeQuery = true)
    void insertRecipeNer(@Param("recId") Integer recId, @Param("nerId") Integer nerId);

    @Transactional
    @Modifying
    @Query(value = "insert into recipe_step (recipe_id, step_id) values (:recId, :steId)", nativeQuery = true)
    void insertRecipeStep(@Param("recId") Integer recId, @Param("steId") Integer steId);

    @Transactional
    @Modifying
    @Query(value = "insert into ner_mol (ner_id, pubchem_id) values (:nerId, :pubchemId)", nativeQuery = true)
    void insertNerMol(@Param("nerId") Integer nerId, @Param("pubchemId") Integer pubchemId);


    // Get ID's;

    @Query(value = "select max(i.ID) from ner i where i.tag_value = :name", nativeQuery = true)
    Integer findIngredientId(@Param("name") String name);

    @Query(value = "select max(i.ID) from step i where i.tag_value = :name", nativeQuery = true)
    Integer findStepId(@Param("name") String name);

    @Query(value = "select max(i.ID) from recipe i where i.tag_value = :name", nativeQuery = true)
    Integer findRecipeId(@Param("name") String name);

    @Query(value = "select max(i.pubchem_id) from mol i where i.common_name = :name", nativeQuery = true)
    Integer findMoleculeId(@Param("name") String name);
}
