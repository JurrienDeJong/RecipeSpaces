package nl.bioinf.recipespaces.recipespaces.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IngredientAmountRepository extends JpaRepository<IngredientAmount, String> {
    @Query(value = "SELECT DISTINCT i.id, i.tag_value from ingredient_amount i join recipe_ingredients ri on i.ID = ri.ingredient_id where ri.recipe_id = :id", nativeQuery = true)
    Set<IngredientAmount> ingredientAmountFromRecipe(@Param("id") Integer recipeID);
}