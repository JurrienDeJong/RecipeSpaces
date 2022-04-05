package nl.bioinf.recipespaces.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {
    @Query(value = "SELECT DISTINCT r.ID, r.tag_value from recipe r join recipe_ner rn on r.ID = rn.recipe_id where rn.ner_id like %:id% limit 1000", nativeQuery = true)
    Set<Recipe> recipesFromIngredient(@Param("id") Integer id);

    @Query(value = "select * from recipe r where r.tag_value like %:keyword% limit 250", nativeQuery = true)
    List<Recipe> recipesFromKeyword(@Param("keyword") String keyword);

    @Query(value = "select * from recipe r where r.id = :id", nativeQuery = true)
    Recipe findRecipeById(@Param("id") Integer id);

    @Query(value = "select * from recipe limit 100000", nativeQuery = true)
    Set<Recipe> selectAllRecipes();

    @Query(value = "select distinct r.id from recipe r where r.tag_value = :tag_value", nativeQuery = true)
    Integer missingId(@Param("tag_value") String tagValue);


}

