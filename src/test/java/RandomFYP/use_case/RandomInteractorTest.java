package RandomFYP.use_case;

import RandomFYP.data_access.RandomDataAccess;
import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class RandomInteractorTest {

    private RandomDataAccess mockDataAccess;
    private RandomInteractor interactor;

    private RandomOutput output;

    @BeforeEach
    void setUp() throws IOException {
        interactor = new RandomInteractor();
        output = interactor.getRecipeList();
    }

    @Test
    void getRecipeList() throws IOException {

        assertNotNull(output);

    }

    @Test
    void test_size_of_recipe_list() throws IOException {
        List<Recipe> recipeList = output.getRecipeList();
        assertEquals(10, recipeList.size());
    }

    @Test
    void Assert_all_Recipes() throws IOException {
        List<Recipe> recipeList = output.getRecipeList();

        for (Recipe recipe : recipeList) {
            assertNotNull(recipe);
            assertEquals(Recipe.class, recipe.getClass());
        }

        }

}
