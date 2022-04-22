package nl.bioinf.recipespaces.dao;

import nl.bioinf.recipespaces.model.IngredientAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;
import java.util.Set;
/**
 * Statements used by IngredientAmountService
 * @author Rose Hazenberg
 */
@Repository
public interface IngredientAmountRepository extends JpaRepository<IngredientAmount, String> {
    @Query(value = "SELECT i.id, i.tag_value from ingredient_amount i join recipe_ingredients ri on i.ID = ri.ingredient_id where ri.recipe_id = :id", nativeQuery = true)
    IngredientAmount ingredientAmountFromRecipe(@Param("id") Integer recipeID);

    @Query(value = "SELECT distinct i.id, i.tag_value from ingredient_amount i join recipe_ingredients ri on i.ID = ri.ingredient_id where ri.recipe_id = :id", nativeQuery = true)
    List<IngredientAmount> ingredientAmountsFromRecipe(Integer id);
}