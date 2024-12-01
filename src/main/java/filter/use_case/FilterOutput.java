package filter.use_case;

import java.util.List;

import entity.Recipe;

/**
 * The FilterOutput class represents the output of the filter use case.
 */
public class FilterOutput {
    private final List<Recipe> recipeList;

    public FilterOutput(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public List<Recipe> getRecipeList1() {
        return recipeList;
    }
}
