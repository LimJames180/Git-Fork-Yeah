package RandomFYP.use_case;

import RandomFYP.data_access.RandomDataAccess;
import entity.Recipe;

import java.io.IOException;
import java.util.List;

public class RandomInteractor {

    RandomDataAccess Access = new RandomDataAccess();
    public RandomOutput getRecipeList() throws IOException {
        RandomOutput output = new RandomOutput(Access.fetchrandom());
        return output;
    }


}
