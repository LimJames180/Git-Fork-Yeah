package use_case.filter;


import entity.Recipe;

import java.util.List;

public interface FilterInputBoundary {
    void filterRecipes(FilterInput input);


    List<Recipe> getRecipeList();

}
