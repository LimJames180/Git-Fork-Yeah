package filter.use_case;

import entity.Recipe;

import java.util.List;

public class FilterOutput {
    List<Recipe> recipeList;

    public FilterOutput(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public List<Recipe> getRecipeList1() {
        return recipeList;
    }
}
