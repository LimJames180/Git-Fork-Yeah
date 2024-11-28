package filter.interface_adapter;

import entity.Recipe;
import filter.use_case.FilterOutput;

import java.util.List;

public class FilterViewModel {
    private List<Recipe> recipeList;

    public void setFilterOutput(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }
}
