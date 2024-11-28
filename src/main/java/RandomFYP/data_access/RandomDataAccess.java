package RandomFYP.data_access;

import entity.Recipe;
import interface_adapter.RecipeController;

import java.io.IOException;
import java.util.List;

public class RandomDataAccess implements RandomDataAccessInterface {

    @Override
    public List<Recipe> fetchrandom() throws IOException {
        return RecipeController.Random_recipe();
    }
}
