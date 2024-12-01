package RandomFYP.data_access;

import entity.Recipe;
import misc_interface_adapter.RecipeDataAccess;

import java.io.IOException;
import java.util.List;

public class RandomDataAccess implements RandomDataAccessInterface {

    @Override
    public List<Recipe> fetchRandom() throws IOException {
        return RecipeDataAccess.randomRecipe();
    }
}
