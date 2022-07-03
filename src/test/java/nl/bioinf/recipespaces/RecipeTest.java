package nl.bioinf.recipespaces;

import nl.bioinf.recipespaces.dao.RecipeRepository;
import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.Recipe;
import nl.bioinf.recipespaces.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.opentest4j.AssertionFailedError;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * author: Rose Hazenberg
 */

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

        Recipe recipe1 = new Recipe(1, "mac and cheese");
        Recipe recipe2 = new Recipe(2, "vanilla ice cream");
        Recipe recipe3 = new Recipe(3, "beef tacos");

        set.add(recipe1);
        set.add(recipe2);
        set.add(recipe3);

        when(recipeRepository.selectAllRecipes()).thenReturn(set);

        Set<Recipe> recipeSet = recipeService.getAllRecipes();

        assertEquals(3, recipeSet.size());
        verify(recipeRepository, times(1)).selectAllRecipes();
    }


    @Test
    public void getRecipeFromIngredientId_ifNormalUse_thenNormalReturn() {
        Set<Recipe> set = new HashSet<>();

        Recipe recipe = new Recipe(19, "guacamole");
        Ingredient ingredient = new Ingredient("avocado");
        ingredient.setId(123);

        set.add(recipe);

        when(recipeRepository.recipesFromIngredient(ingredient.getId())).thenReturn(set);

        Set<Recipe> recipeSet = recipeService.getRecipesFromIngredient(ingredient.getId());

        assertEquals(1, recipeSet.size());
        verify(recipeRepository, times(1)).recipesFromIngredient(ingredient.getId());
    }

    @Test
    public void findOneRecipe_ifNormalUse_thenNormalReturn() {
        Recipe recipe = new Recipe(19, "guacamole");

        when(recipeRepository.findRecipeById(recipe.getId())).thenReturn(recipe);

        Recipe foundRecipe = recipeService.getId(recipe.getId());

        assertEquals(19, foundRecipe.getId());
        verify(recipeRepository, times(1)).findRecipeById(recipe.getId());
    }

    @Test
    public void IdIsNull_thenThrowException() {
        Recipe recipe = new Recipe(null, "vanilla ice cream");

        when(recipeRepository.findRecipeById(recipe.getId())).thenReturn(recipe);

        Recipe foundRecipe = recipeService.getId(recipe.getId());
        try {
            assertNotNull(foundRecipe.getId());
            fail();
        } catch (AssertionFailedError ex) {
            //pass
        }
        verify(recipeRepository, times(1)).findRecipeById(recipe.getId());
    }
}
