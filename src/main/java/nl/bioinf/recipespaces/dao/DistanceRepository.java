package nl.bioinf.recipespaces.dao;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.IngredientDistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * DAO Which can fetch data related to ingredient distances.
 * @author Jurriën de Jong
 */
@Repository
public interface DistanceRepository extends JpaRepository<IngredientDistance, String> {
    @Query(value = "select * from ner_distances where ner_id = :id", nativeQuery = true)
    List<IngredientDistance> ingredientDistancesFromId(@Param("id") Integer id);

    @Query(value = "SELECT DISTINCT i.id, i.tag_value from ner i join recipe_ner rn on i.ID = rn.ner_id join ner_distances nd on i.ID = nd.ner_id where rn.recipe_id = :id", nativeQuery = true)
    List<IngredientDistance> compareIngredientAgainstAll(@Param("id") Integer id);

    @Query(value = "select afstand from ner_distances where ner_id = :id and target = :target", nativeQuery = true)
    Optional<Double> getDistanceFromPair(@Param("id") Integer id, @Param("target") Integer target);
}

