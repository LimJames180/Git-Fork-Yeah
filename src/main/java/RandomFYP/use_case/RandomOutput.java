package RandomFYP.use_case;

import entity.Recipe;

import java.util.List;

public class RandomOutput {

    List<Recipe> recipeList;

    public RandomOutput(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

}
