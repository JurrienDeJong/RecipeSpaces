package nl.bioinf.recipespaces.recipespaces.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    @Query(value = "SELECT DISTINCT i.id, i.tag_value from ner i join recipe_ner rn on i.ID = rn.ner_id where rn.recipe_id = :id", nativeQuery = true)
    Set<Ingredient> ingredientsFromRecipe(@Param("id") String id);

    @Query(value = "SELECT DISTINCT * from ner n where n.id = :id", nativeQuery = true)
    Ingredient findIngredientById(@Param("id") String id);

    @Query(value = "select distinct ner.id from ner where ner.tag_value = :tag_value", nativeQuery = true)
    String missingId(@Param("tag_value") String tag_value);

    @Query(value = "select * from ner where ner.tag_value like %:keyword%", nativeQuery = true)
    List<Ingredient> ingredientFromKeyword(@Param("keyword") String keyword);
}
