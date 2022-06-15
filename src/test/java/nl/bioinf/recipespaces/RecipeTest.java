package nl.bioinf.recipespaces;

import nl.bioinf.recipespaces.dao.RecipeRepository;
import nl.bioinf.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class RecipeTest {

    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Test
    public void getAllRecipes_ifNormalUse_thenNormalReturn() {
        Set<Recipe> set = new HashSet<>();

        Recipe recipe1 = new Recipe(1, "guacamole");
        Recipe recipe2 = new Recipe(2, "mac and cheese");
        Recipe recipe3 = new Recipe(3, "vanilla ice cream");
        Recipe recipe4 = new Recipe(4, "beef tacos");

        set.add(recipe1);
        set.add(recipe2);
        set.add(recipe3);
        set.add(recipe4);

        when(recipeRepository.selectAllRecipes()).thenReturn(set);

        Set<Recipe> recipeSet = recipeService.getAllRecipes();

        assertEquals(4, recipeSet.size());
        verify(recipeRepository, times(1)).selectAllRecipes();
    }



}
