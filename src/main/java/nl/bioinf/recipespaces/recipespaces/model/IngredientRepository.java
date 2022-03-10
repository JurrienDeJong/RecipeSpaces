package nl.bioinf.recipespaces.recipespaces.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    @Query(value = "SELECT DISTINCT i.id, i.tag_value from ingredient i join recipe_ner rn on i.ID = rn.ner_id where rn.recipe_id = :id", nativeQuery = true)
    Set<Ingredient> ingredientsFromRecipe(@Param("id") String id);
}
