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

        Recipe recipe1 = new Recipe("mac and cheese");
        Recipe recipe2 = new Recipe("vanilla ice cream");
        Recipe recipe3 = new Recipe("beef tacos");

        set.add(recipe1);
        set.add(recipe2);
        set.add(recipe3);

        when(recipeRepository.selectAllRecipes()).thenReturn(set);

        Set<Recipe> recipeSet = recipeService.getAllRecipes();

        assertEquals(3, recipeSet.size());
        verify(recipeRepository, times(1)).selectAllRecipes();
    }



}
