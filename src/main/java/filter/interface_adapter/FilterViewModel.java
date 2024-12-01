package filter.interface_adapter;

import java.util.List;

import entity.Recipe;

/**
 * The FilterViewModel class is responsible for storing the output of the filter use case.
 */
public class FilterViewModel {
    private List<Recipe> recipeList;

    public void setFilterOutput(List<Recipe> newRecipeList) {
        this.recipeList = newRecipeList;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }
}
