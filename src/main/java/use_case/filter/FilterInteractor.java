package use_case.filter;


import data_access.FilterDataAccess;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import data_access.FilterDataAccessInterface;
import entity.Recipe;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FilterInteractor implements FilterInputBoundary {
    private final FilterDataAccessInterface filterDataAccess;
    StringBuilder resultBuilder = new StringBuilder();
    private List<String> complexsearch = new ArrayList<>();
    private List<Recipe> recipeList;



    public FilterInteractor(FilterDataAccess filterDataAccess) {
        this.filterDataAccess = filterDataAccess;
    }


    @Override
    public void filterRecipes(FilterInput input) {
        List<String> ingredients = input.getIngredients();
        Map<String, Boolean> restrictions = input.getRestrictions();
        String ingredientsString = String.join(",", ingredients);
        List<String> some_list = new ArrayList<>();
        for (String key : restrictions.keySet()) {
            if (restrictions.get(key)) {
                some_list.add(key);
            }
        }
        String restrictionsString = String.join(",", some_list);
        if (!restrictionsString.equals("")) {
            complexsearch.add("restrictions="+restrictionsString);
        }
        if (!ingredientsString.equals("")) {
            complexsearch.add("ingredients=" + ingredientsString);
        }
        try {
            recipeList = filterDataAccess.fetchComplexSearch(complexsearch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public StringBuilder getResults() {
        return resultBuilder;
    }
}
