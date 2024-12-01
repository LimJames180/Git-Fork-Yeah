package RandomFYP.interface_adapter;

import RandomFYP.use_case.RandomInteractor;
import entity.Recipe;

import java.io.IOException;
import java.util.List;

public class RandomController {

    RandomInteractor interactor = new RandomInteractor();
    public List<Recipe> randomRecipe() throws IOException {
        return interactor.getRecipeList().getRecipeList();
    }
}