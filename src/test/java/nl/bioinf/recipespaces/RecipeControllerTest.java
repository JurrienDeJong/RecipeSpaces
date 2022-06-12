package nl.bioinf.recipespaces;

import nl.bioinf.recipespaces.dao.RecipeRepository;
import nl.bioinf.recipespaces.model.Recipe;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class RecipeControllerTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @BeforeAll
    static void startUpClass() {
        System.out.println("Connecting to the database");
    }

    @Test
    public void getCountDuplicateRecipe_ifNormal_thenNormalUse() {
        List<Recipe> guacamoles = recipeRepository.recipesFromExactKeyword("guacamole");

        assertNotNull(guacamoles);
        assertEquals(guacamoles.size(), 173);
    }

}
