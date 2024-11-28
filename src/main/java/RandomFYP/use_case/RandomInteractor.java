package RandomFYP.use_case;

import RandomFYP.data_access.RandomDataAccess;
import entity.Recipe;

import java.io.IOException;
import java.util.List;

public class RandomInteractor {

    RandomDataAccess Access = new RandomDataAccess();
    public List<Recipe> getRecipeList() throws IOException {
        return Access.fetchrandom();
    }


}
