package nl.bioinf.recipespaces.dao;

import nl.bioinf.recipespaces.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StepRepository extends JpaRepository<Step, Integer> {
    @Query(value = "SELECT DISTINCT s.id, s.tag_value from step s join recipe_step rs on s.ID = rs.step_id where rs.recipe_id = :id", nativeQuery = true)
    Set<Step> stepsFromRecipe(@Param("id") Integer id);

}
