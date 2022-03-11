package nl.bioinf.recipespaces.recipespaces.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {
    @Query(value = "SELECT DISTINCT * from recipe r join recipe_ner rn on r.ID = rn.recipe_id where r.tag_value like %:tag_value%", nativeQuery = true)
    Set<Recipe> recipesFromIngredient(@Param("tag_value") String tag_value);

    @Query(value = "select * from recipe r where r.tag_value like %:keyword%", nativeQuery = true)
    List<Recipe> recipesFromKeyword(@Param("keyword") String keyword);
}

