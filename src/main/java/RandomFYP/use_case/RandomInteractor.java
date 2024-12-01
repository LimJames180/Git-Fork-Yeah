package RandomFYP.use_case;

import RandomFYP.data_access.RandomDataAccess;

import java.io.IOException;

public class RandomInteractor {

    RandomDataAccess Access = new RandomDataAccess();
    public RandomOutput getRecipeList() throws IOException {
        RandomOutput output = new RandomOutput(Access.fetchRandom());
        return output;
    }


}
