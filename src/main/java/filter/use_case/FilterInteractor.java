package filter.use_case;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.Recipe;
import filter.data_access.FilterDataAccess;
import filter.data_access.FilterDataAccessInterface;

/**
 * The FilterInteractor class implements the FilterInputBoundary interface
 * and handles the filtering of recipes based on the given input criteria.
 */
public class FilterInteractor implements FilterInputBoundary {
    private final FilterDataAccessInterface filterDataAccess;
    private FilterOutputBoundary filterOutputBoundary;
    private List<Recipe> recipeList;

    public FilterInteractor(FilterDataAccess filterDataAccess, FilterOutputBoundary FilterOutputBoundary) {
        this.filterDataAccess = filterDataAccess;
        this.filterOutputBoundary = FilterOutputBoundary;
    }

    @Override
    public void filterRecipes(FilterInput input) throws IOException {
        final List<String> complexsearch = new ArrayList<>();
        final List<String> ingredients = input.getIngredients();
        final Map<String, Boolean> restrictions = input.getRestrictions();
        final Map<String, Boolean> intolerances = input.getIntolerances();
        final int offset = input.getOffset();

        mapToString(restrictions, complexsearch, "diet=");
        mapToString(intolerances, complexsearch, "intolerances=");
        ingredientsToString(ingredients, complexsearch, "includeIngredients=");
        offSetToString(offset, complexsearch);

        recipeList = filterDataAccess.fetchComplexSearch(complexsearch);

        final FilterOutput filterOutput = new FilterOutput(recipeList);
        filterOutputBoundary.setFilterViewModel(filterOutput);
    }

    private static void offSetToString(int offset, List<String> complexsearch) {
        if (offset > 0) {
            complexsearch.add("offset=" + offset);
        }
    }

    private static void ingredientsToString(List<String> ingredients, List<String> complexsearch, String filter) {
        final String ingredientsString = String.join(",", ingredients);
        if (!ingredientsString.isEmpty()) {
            complexsearch.add(filter + ingredientsString);
        }
    }

    private static void mapToString(Map<String, Boolean> restrictions, List<String> complexsearch, String filter) {
        final List<String> someList = new ArrayList<>();
        for (String key : restrictions.keySet()) {
            if (restrictions.get(key)) {
                someList.add(key);
            }
        }
        ingredientsToString(someList, complexsearch, filter);
    }

}
