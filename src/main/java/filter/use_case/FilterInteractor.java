package filter.use_case;

import filter.data_access.FilterDataAccess;
import filter.data_access.FilterDataAccessInterface;
import entity.Recipe;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterInteractor implements FilterInputBoundary {
    private final FilterDataAccessInterface filterDataAccess;
    private FilterOutputBoundary FilterOutputBoundary;
    private List<Recipe> recipeList;

    public FilterInteractor(FilterDataAccess filterDataAccess, FilterOutputBoundary FilterOutputBoundary) {
        this.filterDataAccess = filterDataAccess;
        this.FilterOutputBoundary = FilterOutputBoundary;
    }

    @Override
    public void filterRecipes(FilterInput input) throws IOException {
        List<String> complexsearch = new ArrayList<>();
        List<String> ingredients = input.getIngredients();
        Map<String, Boolean> restrictions = input.getRestrictions();
        Map<String, Boolean> intolerances = input.getIntolerances();
        int offset = input.getOffset();


        mapToString(restrictions, complexsearch, "diet=");
        mapToString(intolerances, complexsearch, "intolerances=");
        ingredientsToString(ingredients, complexsearch, "includeIngredients=");
        offSetToString(offset, complexsearch);

        recipeList = filterDataAccess.fetchComplexSearch(complexsearch);

        FilterOutput filterOutput = new FilterOutput(recipeList);
        FilterOutputBoundary.setFilterViewModel(filterOutput);
    }

    private static void offSetToString(int offset, List<String> complexsearch) {
        if (offset > 0) {
            complexsearch.add("offset=" + offset);
        }
    }

    private static void ingredientsToString(List<String> ingredients, List<String> complexsearch, String x) {
        String ingredientsString = String.join(",", ingredients);
        if (!ingredientsString.equals("")) {
            complexsearch.add(x + ingredientsString);
        }
    }

    private static void mapToString(Map<String, Boolean> restrictions, List<String> complexsearch, String x) {
        List<String> some_list = new ArrayList<>();
        for (String key : restrictions.keySet()) {
            if (restrictions.get(key)) {
                some_list.add(key);
            }
        }
        ingredientsToString(some_list, complexsearch, x);
    }

}
